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

    // Root mapp för eventbilder, kan konfigureras via miljövariabeln EVENT_IMAGE_ROOT i docker-compose.yml
    private static final String ROOT = System.getenv().getOrDefault(
            "EVENT_IMAGE_ROOT",
            "/opt/assets/events"
    );

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {

            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/") || pathInfo.contains("..")) {
                resp.sendError(400);
                return;
            }

            Path root = Path.of(ROOT).normalize();
            Path file = root.resolve(pathInfo.substring(1)).normalize();

            if (!file.startsWith(root)) {
                resp.sendError(403);
                return;
            }
            if (!Files.exists(file) || Files.isDirectory(file)) {
                resp.sendError(404);
                return;
            }

            String ct = Files.probeContentType(file);
            if (ct == null) ct = "application/octet-stream";
            resp.setContentType(ct);
            resp.setHeader("Cache-Control", "public, max-age=3600");

            try (OutputStream os = resp.getOutputStream()) {
                Files.copy(file, os);
            }
        } catch (Exception e) {
            try { resp.sendError(500); } catch (Exception ignored) {}
        }
    }
}