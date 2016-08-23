package alexander.magazine.domain_layer;

/**
 * Created by zumba on 27.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 * This class is used to reprisent history of data stored for every change.
 */
public class ArticleHistory {
    public final Integer id;
    public final Integer articleId;
    public final String title;
    public final String body;
    public final String authorName;

    public ArticleHistory(Integer id, Integer articleId, String title, String body, String authorName) {
        this.id = id;
        this.articleId = articleId;
        this.title = title;
        this.body = body;
        this.authorName = authorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleHistory that = (ArticleHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (articleId != null ? !articleId.equals(that.articleId) : that.articleId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        return authorName != null ? authorName.equals(that.authorName) : that.authorName == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (articleId != null ? articleId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }
}
