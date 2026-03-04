package com.dt170g.g3.backend.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Named("eventBean")
@ViewScoped
public class EventBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String ROOT = System.getenv().getOrDefault("EVENT_IMAGE_ROOT", "/opt/assets/events");
    private static final DateTimeFormatter DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final Set<String> IMG_EXT = Set.of(".jpg", ".jpeg", ".png", ".webp");

    private static final DateTimeFormatter HERO_DATE =
            DateTimeFormatter.ofPattern("EEEE d MMMM yyyy, HH:mm", new Locale("sv", "SE"));

    private static final DateTimeFormatter COMMENT_DATE =
            DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", new Locale("sv", "SE"));

    // =======================
    // Mock: kommande event för hero-overlay
    // =======================

    public static class UpcomingEvent implements Serializable {
        private String title;
        private String description;
        private LocalDateTime eventDate;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public LocalDateTime getEventDate() { return eventDate; }
        public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }
    }

    private UpcomingEvent upcomingHeroEvent;

    // =======================
    // Models (JSF-friendly)
    // =======================

    public static class EventDraft implements Serializable {
        private long id;
        private String folder;
        private String title;
        private String description;
        private LocalDateTime eventDate;

        public long getId() { return id; }
        public void setId(long id) { this.id = id; }

        public String getFolder() { return folder; }
        public void setFolder(String folder) { this.folder = folder; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public LocalDateTime getEventDate() { return eventDate; }
        public void setEventDate(LocalDateTime eventDate) { this.eventDate = eventDate; }
    }

    public static class PostDraft implements Serializable {
        private long id;
        private long eventId;
        private String imgFile;
        private String imgUrl;

        public long getId() { return id; }
        public void setId(long id) { this.id = id; }

        public long getEventId() { return eventId; }
        public void setEventId(long eventId) { this.eventId = eventId; }

        public String getImgFile() { return imgFile; }
        public void setImgFile(String imgFile) { this.imgFile = imgFile; }

        public String getImgUrl() { return imgUrl; }
        public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    }

    public static class CommentDraft implements Serializable {
        private long id;
        private long eventId;
        private String name;
        private String notes;
        private LocalDateTime createdAt;

        public long getId() { return id; }
        public void setId(long id) { this.id = id; }

        public long getEventId() { return eventId; }
        public void setEventId(long eventId) { this.eventId = eventId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getNotes() { return notes; }
        public void setNotes(String notes) { this.notes = notes; }

        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

        // Praktiskt för UI
        public String getCreatedAtText() {
            if (createdAt == null) return "";
            return createdAt.format(COMMENT_DATE);
        }
    }

    // =======================
    // State
    // =======================

    private List<EventDraft> pastEvents = new ArrayList<>();
    private Map<Long, List<PostDraft>> postsByEvent = new HashMap<>();
    private Map<Long, List<CommentDraft>> commentsByEvent = new HashMap<>();
    private Long selectedPastEventId;

    // Form-fält (JSF binder hit)
    private String newCommentName;
    private String newCommentNotes;

    // enkel id-sekvens för mock
    private long commentSeq = 1;

    @PostConstruct
    public void init() {
        refresh();
    }

    public void refresh() {
        rebuildFromFolders();
        loadUpcomingHeroMock();
        loadMockComments();
    }

    // =======================
    // HERO: Mock-data
    // =======================

    private void loadUpcomingHeroMock() {
        UpcomingEvent u = new UpcomingEvent();
        u.setTitle("Livekväll med trubadur");
        u.setDescription("En kväll med lokala smaker, bra stämning och live-musik. Begränsat antal platser.");
        u.setEventDate(LocalDateTime.now().plusDays(10).withHour(19).withMinute(0).withSecond(0).withNano(0));
        this.upcomingHeroEvent = u;
    }

    public String getHeroTitle() {
        String t = (upcomingHeroEvent != null) ? upcomingHeroEvent.getTitle() : null;
        return notBlank(t) ? t : "Kommande event";
    }

    public String getHeroDescription() {
        String d = (upcomingHeroEvent != null) ? upcomingHeroEvent.getDescription() : null;
        return notBlank(d) ? d : "Håll utkik – mer info kommer snart.";
    }

    public String getHeroDateText() {
        if (upcomingHeroEvent == null || upcomingHeroEvent.getEventDate() == null) return "";
        return upcomingHeroEvent.getEventDate().format(HERO_DATE);
    }

    private boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

    // =======================
    // Mock comments
    // =======================

    private void loadMockComments() {
        commentSeq = 1;
        Map<Long, List<CommentDraft>> map = new HashMap<>();

        for (EventDraft e : pastEvents) {
            List<CommentDraft> list = new ArrayList<>();

            int count = (int) (e.getId() % 4); // 0–3 kommentarer
            for (int i = 0; i < count; i++) {
                CommentDraft c = new CommentDraft();
                c.setId(commentSeq++);
                c.setEventId(e.getId());
                c.setName(switch (i) {
                    case 0 -> "Anton";
                    case 1 -> "Sara";
                    default -> "Gäst";
                });
                c.setNotes(switch (i) {
                    case 0 -> "Riktigt bra kväll! Maten var 10/10.";
                    case 1 -> "Tack för grymt event — kommer igen!";
                    default -> "Så kul, fler såna här!";
                });
                c.setCreatedAt(LocalDateTime.now().minusDays(2 + i).withSecond(0).withNano(0));
                list.add(c);
            }

            list.sort(Comparator.comparing(CommentDraft::getCreatedAt).reversed());
            map.put(e.getId(), list);
        }

        this.commentsByEvent = map;
    }

    // =======================
    // Add comment (mock - sparar i minnet)
    // =======================

    public void addComment() {
        FacesContext fc = FacesContext.getCurrentInstance();

        if (selectedPastEventId == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Välj ett event först.", null));
            return;
        }

        String notes = (newCommentNotes == null) ? "" : newCommentNotes.trim();
        if (notes.isEmpty()) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Kommentaren får inte vara tom.", null));
            return;
        }

        CommentDraft c = new CommentDraft();
        c.setId(commentSeq++);
        c.setEventId(selectedPastEventId);

        String name = (newCommentName == null) ? "" : newCommentName.trim();
        c.setName(name.isEmpty() ? null : name);

        c.setNotes(notes);
        c.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));

        commentsByEvent.computeIfAbsent(selectedPastEventId, k -> new ArrayList<>()).add(0, c);

        // nollställ form
        newCommentName = "";
        newCommentNotes = "";

        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Kommentaren är tillagd (mock).", null));
    }

    // =======================
    // Core logic: rebuild from folders
    // =======================

    private void rebuildFromFolders() {
        Path pastDir = Path.of(ROOT).resolve("past");

        List<LocalDate> dates = listDateFolders(pastDir);

        List<EventDraft> events = new ArrayList<>();
        Map<Long, List<PostDraft>> posts = new HashMap<>();

        long eventSeq = 10;
        for (LocalDate d : dates) {
            String folder = d.format(DATE);

            EventDraft e = new EventDraft();
            e.setId(eventSeq++);
            e.setFolder(folder);
            e.setTitle("Event " + folder);
            e.setDescription("");
            e.setEventDate(d.atTime(18, 0));
            events.add(e);

            List<String> images = listImagesSortedByLastModifiedDesc(pastDir.resolve(folder));

            List<PostDraft> pList = new ArrayList<>();
            long basePostId = e.getId() * 1000L;

            for (int i = 0; i < images.size(); i++) {
                String fn = images.get(i);

                PostDraft p = new PostDraft();
                p.setId(basePostId + (i + 1));
                p.setEventId(e.getId());
                p.setImgFile(fn);
                p.setImgUrl(getContextPath() + "/event-images/past/" + folder + "/" + fn);
                pList.add(p);
            }

            posts.put(e.getId(), pList);
        }

        this.pastEvents = events;
        this.postsByEvent = posts;

        if (selectedPastEventId == null || !postsByEvent.containsKey(selectedPastEventId)) {
            selectedPastEventId = pastEvents.isEmpty() ? null : pastEvents.get(0).getId();
        }
    }

    private List<LocalDate> listDateFolders(Path pastDir) {
        if (!Files.isDirectory(pastDir)) return List.of();

        try (var s = Files.list(pastDir)) {
            return s.filter(Files::isDirectory)
                    .map(p -> p.getFileName().toString())
                    .map(this::parseDateOrNull)
                    .filter(Objects::nonNull)
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

    private LocalDate parseDateOrNull(String folderName) {
        try { return LocalDate.parse(folderName, DATE); }
        catch (Exception e) { return null; }
    }

    private List<String> listImagesSortedByLastModifiedDesc(Path dir) {
        if (!Files.isDirectory(dir)) return List.of();

        try (var s = Files.list(dir)) {
            List<Path> files = s.filter(Files::isRegularFile)
                    .filter(p -> isImage(p.getFileName().toString()))
                    .collect(Collectors.toList());

            files.sort((a, b) -> {
                try {
                    var tb = Files.getLastModifiedTime(b);
                    var ta = Files.getLastModifiedTime(a);
                    return tb.compareTo(ta);
                } catch (Exception e) {
                    return 0;
                }
            });

            return files.stream()
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            return List.of();
        }
    }

    private boolean isImage(String name) {
        String lower = name.toLowerCase(Locale.ROOT);
        for (String ext : IMG_EXT) if (lower.endsWith(ext)) return true;
        return false;
    }

    private String getContextPath() {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestContextPath();
    }

    // =======================
    // UI getters/actions
    // =======================

    public List<EventDraft> getPastEvents() { return pastEvents; }

    public Long getSelectedPastEventId() { return selectedPastEventId; }

    public void selectPastEvent(long id) { selectedPastEventId = id; }

    public EventDraft getSelectedPastEvent() {
        if (selectedPastEventId == null) return null;
        return pastEvents.stream()
                .filter(e -> e.getId() == selectedPastEventId)
                .findFirst()
                .orElse(null);
    }

    public List<PostDraft> getSelectedPosts() {
        if (selectedPastEventId == null) return List.of();
        return postsByEvent.getOrDefault(selectedPastEventId, List.of());
    }

    public List<CommentDraft> getSelectedComments() {
        if (selectedPastEventId == null) return List.of();
        return commentsByEvent.getOrDefault(selectedPastEventId, List.of());
    }

    // Form getters/setters
    public String getNewCommentName() { return newCommentName; }
    public void setNewCommentName(String newCommentName) { this.newCommentName = newCommentName; }

    public String getNewCommentNotes() { return newCommentNotes; }
    public void setNewCommentNotes(String newCommentNotes) { this.newCommentNotes = newCommentNotes; }
}