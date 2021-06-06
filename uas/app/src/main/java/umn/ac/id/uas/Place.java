package umn.ac.id.uas;

public class Place {
    private String PlaceName;
    private String Desc;
    private String ImageUrl;
    private String Latitude;
    private String Longtitude;

    public Place(String placeName, String desc, String imageUrl, String latitude, String longtitude) {
        PlaceName = placeName;
        Desc = desc;
        ImageUrl = imageUrl;
        Latitude = latitude;
        Longtitude = longtitude;
    }

    public Place() {
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }


}
