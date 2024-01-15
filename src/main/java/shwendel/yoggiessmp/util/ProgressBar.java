package shwendel.yoggiessmp.util;

import org.bukkit.ChatColor;

public class ProgressBar {

    private static final int DEFAULT_BARS = 16;
    private static final ChatColor DEFAULT_FILL_COLOR = ChatColor.GREEN;
    private static final ChatColor DEFAULT_EMPTY_COLOR = ChatColor.RED;

    /**
     * Get a really awesome progress bar
     * @param progress The progress e.g. 0.75 (75%), 1 (100%), 0 (0%) or 0.52 (52%)
     * @param bars The amount of bars -> || 100% || is 4
     * @param fillColor The ChatColor when the delimiter (The individual bar) is filled
     * @param emptyColor The ChatColor when the delimiter isn't hit
     * @return The really awesome progress bar
     */
    public static String getProgressBar(double progress, int bars, ChatColor fillColor, ChatColor emptyColor) {

        String progressBar = "";

        for(int i = 0; i <= bars; i++) {

            if(i == bars / 2) {

                progressBar += " " + ChatColor.YELLOW + ((int) Math.ceil(progress * 100)) + "% ";

            } else {

                if(progress >= (double) i / bars) {

                    progressBar += fillColor + "|";

                } else {

                    progressBar += emptyColor + "|";

                }

            }

        }

        return progressBar;
    }

    public static String getProgressBar(double progress, int bars) {
        return getProgressBar(progress, bars, DEFAULT_FILL_COLOR, DEFAULT_EMPTY_COLOR);
    }

    public static String getProgressBar(double progress) {
        return getProgressBar(progress, DEFAULT_BARS, DEFAULT_FILL_COLOR, DEFAULT_EMPTY_COLOR);
    }

}