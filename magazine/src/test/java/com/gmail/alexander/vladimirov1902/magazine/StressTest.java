package com.gmail.alexander.vladimirov1902.magazine;

import com.gmail.alexander.vladimirov1902.magazine.domain_layer.Article;
import com.gmail.alexander.vladimirov1902.magazine.domain_layer.ArticleRepository;
import com.gmail.alexander.vladimirov1902.magazine.persistence_layer.DataStore;
import com.gmail.alexander.vladimirov1902.magazine.persistence_layer.PersistenceArticleRepository;
import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by zumba on 28.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class StressTest {
    @Rule
    public DataBaseConnectionRule databaseConnectionRule = new DataBaseConnectionRule();
 /* @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(databaseConnectionRule.connection),
            new LinkedList<String>() {{
                add("article");
                add("article_history");
            }});*/
    private ArticleRepository articleRepository = new PersistenceArticleRepository(databaseConnectionRule.connection);

    public StressTest() throws SQLException {
    }

    @Test(timeout = 180000L)
    public void oneMillionArticles(){

        DataStore dataStore = new DataStore(databaseConnectionRule.connection);
        dataStore.update("SET autocommit=0;");
       for (int i =0 ; i <1000000; i++) {
           articleRepository.register(new Article(i, "1", "2", "3"));
       }
        dataStore.update("COMMIT;");
  }
}
