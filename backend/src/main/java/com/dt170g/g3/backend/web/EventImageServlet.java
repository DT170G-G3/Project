package com.dt170g.g3.backend.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/event-images/*")
public class EventImageServlet extends HttpServlet {

    /**
     * Root-mapp för eventbilder på filsystemet.
     * I Docker mappar man till en host-mapp (t.ex. ../assets/events) till /opt/assets/events i containern,
     * och kan även styra sökvägen via miljövariabeln EVENT_IMAGE_ROOT.
     */
    private static final String ROOT = System.getenv().getOrDefault(
            "EVENT_IMAGE_ROOT",
            "/opt/assets/events"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // pathInfo är delen efter "/event-images", t.ex. "/past/2026-01-07/4in1.png"
            String pathInfo = req.getPathInfo();

            // Grundvalidering: måste finnas en filväg, vi blockar enkla path traversal-försök ("..")
            if (pathInfo == null || pathInfo.equals("/") || pathInfo.contains("..")) {
                resp.sendError(400); // Bad Request
                return;
            }

            // Normalisera root för att få en jämförbar bas (tar bort t.ex. "a/../b")
            Path root = Path.of(ROOT).normalize();

            // Bygg fram full sökväg: root + (pathInfo utan inledande "/")
            // normalize igen för att städa bort eventuella ".." eller konstiga segment
            Path file = root.resolve(pathInfo.substring(1)).normalize();

            // Extra säkerhet: om någon försöker ta sig utanför root genom specialvägar,
            // så stoppar vi det här (file måste ligga under root).
            if (!file.startsWith(root)) {
                resp.sendError(403); // Forbidden
                return;
            }

            // Fil måste finnas och vara en vanlig fil (inte katalog)
            if (!Files.exists(file) || Files.isDirectory(file)) {
                resp.sendError(404); // Not Found
                return;
            }

            // Försök detektera content-type (image/png, image/jpeg, etc.)
            // Om systemet inte kan avgöra, fallback till binary stream.
            String ct = Files.probeContentType(file);
            if (ct == null) ct = "application/octet-stream";
            resp.setContentType(ct);

            // Cache-header: browser får cacha i 1 timme.
            resp.setHeader("Cache-Control", "public, max-age=3600");

            // Streama filen direkt till HTTP-svaret
            try (OutputStream os = resp.getOutputStream()) {
                Files.copy(file, os);
            }

        } catch (Exception e) {
            // Om något oväntat händer: returnera 500 utan att läcka interna detaljer
            try {
                resp.sendError(500); // Internal Server Error
            } catch (Exception ignored) {}
        }
    }
}