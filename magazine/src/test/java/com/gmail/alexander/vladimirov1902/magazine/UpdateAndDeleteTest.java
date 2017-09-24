package com.gmail.alexander.vladimirov1902.magazine;

import com.gmail.alexander.vladimirov1902.magazine.domain_layer.Article;
import com.gmail.alexander.vladimirov1902.magazine.domain_layer.ArticleRepository;
import com.gmail.alexander.vladimirov1902.magazine.persistence_layer.DataStore;
import com.gmail.alexander.vladimirov1902.magazine.persistence_layer.PersistenceArticleRepository;
import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zumba on 27.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class UpdateAndDeleteTest {
    @Rule
    public DataBaseConnectionRule databaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(databaseConnectionRule.connection),
            new LinkedList<String>() {{
                add("article");
                add("article_history");
            }});
    private ArticleRepository articleRepository = new PersistenceArticleRepository(databaseConnectionRule.connection);


    public UpdateAndDeleteTest() throws SQLException {
    }

    @Test
    public void deleteArticle() {
        articleRepository.register(new Article(1, "1", "2", "3"));
        articleRepository.delete(1);
        List<Article> actual = articleRepository.all();
        assertThat(actual.isEmpty(),is(true));
    }

}
