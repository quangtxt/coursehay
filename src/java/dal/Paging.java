
package dal;

import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Paging{
    private int page;
    private int endPage;
    private int size;
    private int range;
    
    public int getPage() {
        return page;
    }

    public void setPage(String page) {
        if (page == null || Integer.parseInt(page) < 1 || Integer.parseInt(page) > getEndPage()) {
            this.page = 1;
        } else {
            this.page = Integer.parseInt(page);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setRange(String range,int[] amountCourseIn1Page) {
        if (range == null || Integer.parseInt(range) < amountCourseIn1Page[0] || Integer.parseInt(range) > amountCourseIn1Page[amountCourseIn1Page.length-1]) {
            this.range = 5;
        } else {
            this.range = Integer.parseInt(range);
        }
    }

    public int getRange() {
        return range;
    }

    public int getEndPage() {
        endPage = size / range;
        if (size % range != 0) {
            endPage++;
        }
        return endPage;
    }
    public ArrayList getListPani(ArrayList list) {
        ArrayList listPani = new ArrayList();
        int max= size;
        if (size>page*range) {
            max = page*range;
        }
        for (int i = (page-1)*range; i < max; i++) {
            listPani.add(list.get(i));
        }
        return listPani;
    }
}
