package dao.entity;

import java.util.List;
import java.util.Objects;

public class Page<T> {
    private List<T> elements;
    private List<String> countOfPages;

    public Page(List<T> elements, List<String> countOfPage) {
        this.elements = elements;
        this.countOfPages = countOfPage;
    }

    public Page() {

    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }

    public void setCountOfPages(List<String> countOfPages) {
        this.countOfPages = countOfPages;
    }

    public List<T> getElements() {
        return elements;
    }

    public List<String> getCountOfPages() {
        return countOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page<?> page = (Page<?>) o;

        if (!Objects.equals(elements, page.elements)) return false;
        return Objects.equals(countOfPages, page.countOfPages);
    }

    @Override
    public int hashCode() {
        int result = elements != null ? elements.hashCode() : 0;
        result = 31 * result + (countOfPages != null ? countOfPages.hashCode() : 0);
        return result;
    }
}
