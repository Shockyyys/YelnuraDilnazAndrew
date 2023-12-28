public class Movie {
    private String title;
    private String genre;
    private int ageRestriction;
    private int duration;
    private int releaseYear;
    private String director;

    public Movie(String title, String genre, int ageRestriction, int duration, int releaseYear, String director) {
        this.title = title;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.director = director;
    }

    // Getter methods for title, genre, and ageRestriction
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    // New getter methods for duration, releaseYear, and director
    public int getDuration() {
        return duration;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getDirector() {
        return director;
    }
}
