package cn.shaojiel.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaskingPatternLayout extends PatternLayout {
    private static final String MASK = "******";

    private Pattern multilinePattern;
    private List<String> maskPatterns = new ArrayList<>();

    public void addMaskPattern(String maskPattern) {
        maskPatterns.add(maskPattern);
        multilinePattern = Pattern.compile(maskPatterns.stream().collect(Collectors.joining("|")),
                Pattern.MULTILINE);
    }

    /*private String maskMessage(String message) {
        if (multilinePattern == null) {
            return message;
        }
        StringBuilder sb = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(sb);
        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group), matcher.end(group)).forEach(i -> sb.setCharAt(i, '*'));
                }
            });
        }
        return sb.toString();
    }*/

    private String maskMessage(String message) {

        if (multilinePattern == null) {
            return message;
        }

        StringBuilder sb = new StringBuilder(message);
        Matcher matcher = multilinePattern.matcher(sb);
        AtomicInteger position = new AtomicInteger(0);
        while (matcher.find(position.get())) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    int start = matcher.start(group);
                    int end = matcher.end(group);

                    sb.replace(start, end, MASK);

                    // The content length which you will mask
                    int contentLength = end - start;


                    /**
                     * The string offset after mask
                     * It means that the string length has become shorter if offset is a positive number
                     * It means that the string length has become longer if offset is a negative number
                     */
                    int offset = contentLength - MASK.length();

                    // The new find position
                    int findPosition = end - offset;

                    // Reset the matcher.find position because of the string length may be changed after mask
                    position.set(findPosition);
                }
            });

        }

        return sb.toString();
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }
}
