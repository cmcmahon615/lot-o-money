package com.cmcmahon615.lotomoney;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Lot O'Money!!!");
        Options settings = Options.setUpGame();
        Stats report = new Stats();
        playUntil(settings, report);
        System.out.println();
        switch (settings.game) {
            case "PB" -> Powerball.showPrizes(report);
            case "MM" -> MegaMillions.showPrizes(report);
            case "LA" -> LottoAmerica.showPrizes(report);
            case "C4L" -> Cash4Life.showPrizes(report);
        }
        report.reportMoneySpent(settings);
        report.reportTimePlayed(settings);
    }

    public static void playUntil(Options options, Stats stats) {
        switch (options.playUntil) {
            case "JP":
                while (!stats.wonJackpot)
                    startSimulation(options, stats);
                break;
            case "PA":
                while (!stats.wonJackpot && stats.prizeMoney < options.stopCondition)
                    startSimulation(options, stats);
                break;
            case "MS":
                while (stats.calculateMoneySpent(options) < options.stopCondition)
                    startSimulation(options, stats);
                break;
            case "YP":
                while (stats.yearsPassed != options.stopCondition)
                    startSimulation(options, stats);
                break;
        }
    }

    public static void startSimulation(Options options, Stats stats) {
        switch (options.game) {
            case "PB" -> Powerball.simulateLottery(options, stats);
            case "MM" -> MegaMillions.simulateLottery(options, stats);
            case "LA" -> LottoAmerica.simulateLottery(options, stats);
            case "C4L" -> Cash4Life.simulateLottery(options, stats);
        }
    }
}