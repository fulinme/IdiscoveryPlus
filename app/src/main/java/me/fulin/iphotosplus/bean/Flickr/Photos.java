package me.fulin.iphotosplus.bean.Flickr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 29/8/15.
 */
public class Photos {

    private Integer page;

    private Integer pages;

    private Integer perpage;

    private Integer total;

    private List<Photo> photo = new ArrayList<Photo>();

    /**
     *
     * @return
     * The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page
     * The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     *
     * @return
     * The pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     *
     * @param pages
     * The pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     *
     * @return
     * The perpage
     */
    public Integer getPerpage() {
        return perpage;
    }

    /**
     *
     * @param perpage
     * The perpage
     */
    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    /**
     *
     * @return
     * The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The photo
     */
    public List<Photo> getPhoto() {
        return photo;
    }

    /**
     *
     * @param photo
     * The photo
     */
    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}
