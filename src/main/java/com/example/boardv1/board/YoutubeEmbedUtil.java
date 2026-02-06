package com.example.boardv1.board;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class YoutubeEmbedUtil {

    // URL 전체 + videoId
    private static final Pattern YT_PATTERN = Pattern.compile(
            "(https?://(?:www\\.)?" +
                    "(?:youtube\\.com/watch\\?v=|youtu\\.be/|youtube\\.com/shorts/)" +
                    "([A-Za-z0-9_-]{6,})" +
                    "(?:[&?][^\\s<\"']*)?" +
                    ")");

    public static String convertKeepLinkAndAppendEmbed(String html) {
        if (html == null)
            return null;

        Document doc = Jsoup.parse(html);
        String bodyHtml = doc.body().html();

        Matcher matcher = YT_PATTERN.matcher(bodyHtml);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String url = matcher.group(1); // 유튜브 URL 전체
            String videoId = matcher.group(2); // videoId

            String iframe = "<div class=\"ratio ratio-16x9\" style=\"margin-top:8px;\">" +
                    "<iframe src=\"https://www.youtube.com/embed/" + videoId + "\" " +
                    "allowfullscreen></iframe>" +
                    "</div>";

            // ✅ 링크는 클릭 가능하게 a 태그로 감싸고, 아래에 iframe 추가
            String replacement = "<a href=\"" + url + "\" target=\"_blank\" rel=\"noopener noreferrer\">" +
                    url +
                    "</a><br/>" +
                    iframe;

            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

}
