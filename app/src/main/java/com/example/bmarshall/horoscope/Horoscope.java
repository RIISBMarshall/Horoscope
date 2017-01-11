package com.example.bmarshall.horoscope;

/**
 * Created by bmarshall on 1/10/17.
 */

public class Horoscope {
    private String name;
    private String sign;
    private String description;
    private String month;
    private String horoscope;

    public static final Horoscope[] horoscopes = new Horoscope[] {
            new Horoscope("Aries",          "Ram",          "Courageous and energetic",                                                "April",     "Your favorite T-shirt brings about your downfall when a literal-minded mob follows its instructions and fills you to the indicated line with margaritas"),
            new Horoscope("Taurus",         "Bull",         "Known for being reliable, practical, ambitious and sensual",              "May",       "Many have felt the Love Which Dare Not Speak Its Name, but you’ll experience the Love That Bellows Its Name Out A Crosstown-Bus Window All Day"),
            new Horoscope("Gemini",         "Twins",        "Gemini-born are clever and intellectual",                                 "June",      "Many have felt the Love Which Dare Not Speak Its Name, but you’ll experience the Love That Bellows Its Name Out A Crosstown-Bus Window All Day"),
            new Horoscope("Cancer",         "Crab",         "Tenacious, loyal and sympathetic",                                        "July",      "Once again, the specter of war will dominate international news, preventing people the world over from learning how you made the world’s largest apple pie"),
            new Horoscope("Leo",            "Lion",         "Warm, action-oriented and driven by the desire to be loved and admired",  "August",    "Your problem is that you have no sense of proportion, which is why you paid surgeons to enlarge your head and hands"),
            new Horoscope("Virgo",          "Virgin",       "Methodical, meticulous, analytical and mentally astute",                  "September", "Some people believe your house in Heaven is filled with all the things you lost while on earth, which explains the dead pets lying everywhere"),
            new Horoscope("Libra",          "Scales",       "Librans are famous for maintaining balance and harmony",                  "October",   "That run for the record books once again falls short when you start Boston’s all-time second-largest fire"),
            new Horoscope("Scorpio",        "Scorpion",     "Strong willed and mysterious",                                            "November",  "The story of the universe has always fascinated you, but the ending will leave you with a lot of unanswered questions"),
            new Horoscope("Sagittarius",    "Archer",       "Born adventurers",                                                        "December",  "You’ll never smile again after the tragic loss of your lower jaw and lips this week"),
            new Horoscope("Capricorn",      "Goat",         "The most determined sign in the Zodiac",                                  "January",   "As it turns out, there are indeed mountains high enough and valleys low enough to keep you from your love"),
            new Horoscope("Aquarius",       "Water Bearer", "Humanitarians to the core",                                               "February",  "The stars wouldn’t take the risks you do, but, hey, it’s your life for the next six months or so"),
            new Horoscope("Pisces",         "Fish",         "Proverbial dreamers of the Zodiac",                                       "March",     "You’ll be trapped in a hell of your own making, forcing you to admit that you really should have put in more bathrooms")
    };

    private Horoscope(String name, String sign, String description, String month, String horoscope) {
        this.name = name;
        this.sign = sign;
        this.description = description;
        this.month = month;
        this.horoscope = horoscope;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public String getDescription() {
        return description;
    }

    public String getMonth() {
        return month;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public String toString() {
        return this.name;
    }

}

