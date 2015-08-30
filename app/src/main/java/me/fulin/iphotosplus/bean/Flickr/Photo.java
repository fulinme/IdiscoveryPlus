package me.fulin.iphotosplus.bean.Flickr;

import android.net.Uri;

/**
 * Created by jack on 29/8/15.
 */
public class Photo {


    private String id;

    private String owner;

    private String secret;

    private String server;

    private Integer farm;

    private String title;

    private Integer ispublic;

    private Integer isfriend;

    private Integer isfamily;


    public Uri getPhotoPageUri()
    {
        //mobile web format https://m.flickr.com/#/photos/90694458@N07/20376186874/
        //web format http://www.flickr.com/photos
        return Uri.parse("https://m.flickr.com/#/photos/"+ owner + "/" + id);
                //.buildUpon()
                //.appendEncodedPath(owner)
                //.appendEncodedPath(id)
                //.build();
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     * The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     *
     * @return
     * The secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     *
     * @param secret
     * The secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     *
     * @return
     * The server
     */
    public String getServer() {
        return server;
    }

    /**
     *
     * @param server
     * The server
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     *
     * @return
     * The farm
     */
    public Integer getFarm() {
        return farm;
    }

    /**
     *
     * @param farm
     * The farm
     */
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The ispublic
     */
    public Integer getIspublic() {
        return ispublic;
    }

    /**
     *
     * @param ispublic
     * The ispublic
     */
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    /**
     *
     * @return
     * The isfriend
     */
    public Integer getIsfriend() {
        return isfriend;
    }

    /**
     *
     * @param isfriend
     * The isfriend
     */
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    /**
     *
     * @return
     * The isfamily
     */
    public Integer getIsfamily() {
        return isfamily;
    }

    /**
     *
     * @param isfamily
     * The isfamily
     */
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

}
