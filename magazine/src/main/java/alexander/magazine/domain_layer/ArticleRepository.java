package alexander.magazine.domain_layer;

import java.util.List;

/**
 * Created by zumba on 27.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This interface provides method to execute queries to databse.
 */
public interface ArticleRepository {
    /**
     * Gets all articles for database.
     * @return list of articles.
     */
    List<Article> all();

    /**
     * Registers article to database.
     * @param article new article that is going to be registered to the database.
     */
    void register(Article article);

    /**
     * Updates article
     * @param article is the article with new changes.
     */
    void update(Article article);

    /**
     * Deletes article by id.
     * @param id of the article that is going to be deleted.
     */
    void delete(Integer id);

    /**
     * Gets part of the history based of limit and offset.
     * @param offset start point ot history ( we may not need the first few records) .
     * @param limit use to limit the history( history may be big so we may need to have limit here(.
     * @return List of Article History.
     */
    List<ArticleHistory> history(Integer offset, Integer limit);
}
