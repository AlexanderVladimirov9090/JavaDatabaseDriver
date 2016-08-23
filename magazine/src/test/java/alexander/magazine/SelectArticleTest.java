package alexander.magazine;

import alexander.magazine.domain_layer.Article;
import alexander.magazine.domain_layer.ArticleHistory;
import alexander.magazine.domain_layer.ArticleRepository;
import alexander.magazine.persistence_layer.DataStore;
import alexander.magazine.persistence_layer.PersistenceArticleRepository;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zumba on 27.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class SelectArticleTest {
    @Rule
    public DataBaseConnectionRule databaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(databaseConnectionRule.connection),
            new LinkedList<String>() {{
                add("article");
                add("article_history");
            }});
    private Connection connection = databaseConnectionRule.connection;
    private ArticleRepository articleRepository = new PersistenceArticleRepository(databaseConnectionRule.connection);

    public SelectArticleTest() throws SQLException {
    }

    @Test
    public void getAllArticles() {
        List<Article> expected = new LinkedList<>();
        expected.add(new Article(1, "Some Title", "Some Body", "Some Author"));
        expected.add(new Article(2, "Title", "Body", "Author"));
        articleRepository.register(new Article(1, "Some Title", "Some Body", "Some Author"));
        articleRepository.register(new Article(2, "Title", "Body", "Author"));
        List<Article> actual = articleRepository.all();
        assertThat(actual,is(equalTo(expected)));
    }

    @Test
    public void getHistoryFromStart(){
        List<ArticleHistory> expected = new LinkedList<>();
        articleRepository.register(new Article(1, "zzzz", "zzzzz", "zzzzz"));
        articleRepository.register(new Article(2, "aaaaa", "aaaaa", "aaaaa"));
        expected.add(new ArticleHistory(2,1, "zzzz", "zzzzz", "zzzzz"));
        expected.add(new ArticleHistory(2,2, "aaaaa", "aaaaa", "aaaaa"));
        articleRepository.update(new Article(1,"No","No","No"));
        articleRepository.update(new Article(2,"Yes","Yes","Yes"));
        List<ArticleHistory> actual = articleRepository.history(0,2);
        assertThat(actual,is(equalTo(expected)));
    }

    @Test
    public void getHistoryFromSomePoint(){
        List<ArticleHistory> expected = new LinkedList<>();
        articleRepository.register(new Article(1, "zzzz", "zzzzz", "zzzzz"));
        articleRepository.register(new Article(2, "aaaaa", "aaaaa", "aaaaa"));
        expected.add(new ArticleHistory(2,2, "aaaaa", "aaaaa", "aaaaa"));
        articleRepository.update(new Article(1,"No","No","No"));
        articleRepository.update(new Article(2,"Yes","Yes","Yes"));
        List<ArticleHistory> actual = articleRepository.history(1,1);
        assertThat(actual,is(equalTo(expected)));
    }
}