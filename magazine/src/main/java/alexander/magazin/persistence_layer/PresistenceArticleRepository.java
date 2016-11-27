package alexander.magazin.persistence_layer;

import alexander.magazine.domain_layer.Article;
import alexander.magazine.domain_layer.ArticleHistory;
import alexander.magazine.domain_layer.ArticleRepository;

import java.sql.Connection;
import java.util.List;

/**
 * Created by zumba on 27.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class PresistenceArticleRepository implements ArticleRepository {
    private final DataStore dataStore;

    public PresistenceArticleRepository(Connection connection) {
        this.dataStore = new DataStore(connection);
    }

    @Override
    public List<Article> all() {
        return dataStore.fetchRows("SELECT * FROM article", resultSet -> new Article(
                resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
    }

    @Override
    public void register(Article article) {
        dataStore.update("INSERT INTO article VALUES(?,?,?,?)", article.id, article.title, article.body, article.authorName);
    }

    @Override
    public void update(Article article) {
        dataStore.update("UPDATE article SET ? = Title, ? = Body, ? = Author WHERE ? = Id", article.title, article.body, article.authorName, article.id);
    }

    @Override
    public void delete(Integer id) {
        dataStore.update("DELETE FROM article WHERE ?= Id", id);
    }

    @Override
    public List<ArticleHistory> history(Integer offset, Integer limit) {

        return dataStore.fetchRows("SELECT * FROM article_history LIMIT ? OFFSET ?",
                resultSet -> new ArticleHistory(resultSet.getInt(1),
                        resultSet.getInt(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5)), limit, offset);
    }
}
