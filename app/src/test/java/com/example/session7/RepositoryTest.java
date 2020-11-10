package com.example.session7;


import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.session7.model.Article;
import com.example.session7.model.DBSlideModel;
import com.example.session7.model.Folder;
import com.example.session7.model.JokeWithoutStatus2;
import com.example.session7.persistence.ApplicationRepository;
import com.example.session7.persistence.local.JokesDao;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class RepositoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private JokesDao jokesDao;

    @Mock
    private Application application;

    private ApplicationRepository repo;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run, false);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }



    @Before
    public void setup()
    {
        Article[] articles = Article.prepopulateArticle();
        List<Article> list = new ArrayList<>();
        for (Article a: articles) {
            list.add(a);
        }
        LiveData<List<Article>> articleMutableLiveData = new MutableLiveData<>(list);


        DBSlideModel[] slideModels = DBSlideModel.prepopulateDBSlideModel();
        List<DBSlideModel> slideModels1 = new ArrayList<>();
        for (DBSlideModel db: slideModels
             ) {
            slideModels1.add(db);
        }
        LiveData<List<DBSlideModel>> slideMutableLiveData = new MutableLiveData<>(slideModels1);

        Folder[] folders = Folder.populateFolder();

        List<Folder> folderList = new ArrayList<>();
        for (Folder folder: folders
             ) {
            folderList.add(folder);
        }

        LiveData<List<Folder>> folderLiveData = new MutableLiveData<>(folderList);


        List<JokeWithoutStatus2> jokeWithoutStatus2List1 = new ArrayList<>();

        jokeWithoutStatus2List1.add(new JokeWithoutStatus2("asdasdas", "I believe this is enough!"));
        jokeWithoutStatus2List1.add(new JokeWithoutStatus2("aaaaaaa", "Don't you think so?"));

        LiveData<List<JokeWithoutStatus2>> listFolder1 = new MutableLiveData<>(jokeWithoutStatus2List1);


        List<JokeWithoutStatus2> jokeWithoutStatus2List2 = new ArrayList<>();

        jokeWithoutStatus2List2.add(new JokeWithoutStatus2("asasadasdas", "I believe thaaaaaaaaais is enough!"));
        jokeWithoutStatus2List2.add(new JokeWithoutStatus2("aaafdsfdaaaa", "Don't you thadink so?"));
        jokeWithoutStatus2List2.add(new JokeWithoutStatus2("asday5sdas", "I believwwfqwe this is enough!"));
        jokeWithoutStatus2List2.add(new JokeWithoutStatus2("aaaafdscxaaa", "Don't fweyou think so?"));

        LiveData<List<JokeWithoutStatus2>> listFolder2 = new MutableLiveData<>(jokeWithoutStatus2List2);




        when(jokesDao.getAllNotes()).thenReturn(folderLiveData);
        when(jokesDao.getArticles()).thenReturn(articleMutableLiveData);
        when(jokesDao.getSlideModels()).thenReturn(slideMutableLiveData);
        when(jokesDao.getJokes(1)).thenReturn(listFolder1);
        when(jokesDao.getJokes(2)).thenReturn(listFolder2);
        List<JokeWithoutStatus2> anyList = new ArrayList<>();
        LiveData<List<JokeWithoutStatus2>> any = new MutableLiveData<>(anyList);
        when(jokesDao.getJokes(any(Integer.class))).thenReturn(any);



        repo = ApplicationRepository.getInstance(any(Application.class));

    }

    @Test
    public void testCreation() {
        assertNotNull(repo);
    }

    @Test
    public void getSlideModels() {
        LiveData<List<SlideModel>> testList = repo.getSlideModels();

        assertNotNull(testList);
        assertNotNull(testList.getValue());
        assertEquals(testList.getValue().size(), 6);
        assertEquals(testList.getValue().get(0).getTitle(), "");
        assertEquals(testList.getValue().get(2).getImageUrl(), R.mipmap.ic_cat_meme_foreground);


    }

    @Test
    public void getArticles() {
        LiveData<List<Article>> testList = repo.getArticles();

        assertNotNull(testList);
        assertNotNull(testList.getValue());
        assertEquals(testList.getValue().get(0), new Article("The most beloved Terrorist, Ahmed, is coming to Denmark. Watch some of his most funny moments. ", R.mipmap.ic_ahmed_foreground, "https://www.youtube.com/watch?v=IL357BrwK7c"));
        assertEquals(testList.getValue().get(2).getImage(),R.mipmap.ic_top_foreground );
        assertEquals(testList.getValue().get(4).getArticleText(), "He made us laugh as children. Find out about the true story of our beloved Jim Carrey!");
    }

    @Test
    public void logIn() {
//        repo.logIn("email", "pass", UserType.PATIENT);
//        verify(firebaseConnection, times(1)).signIn("email", "pass");
    }

    @Test
    public void getFolders() {

        LiveData<List<Folder>> testList = repo.getFolders();

        assertNotNull(testList);
        assertNotNull(testList.getValue());
        assertEquals(testList.getValue().size(), 2);
        assertEquals(testList.getValue().get(0),new Folder("Favorites"));
        assertEquals(testList.getValue().get(1), new Folder("Demo") );

    }

    @Test
    public void getJokes()
    {
        LiveData<List<JokeWithoutStatus2>> testList = repo.getJokes(1);

        assertNotNull(testList);
        assertNotNull(testList.getValue());

        assertEquals(testList.getValue().get(0), new JokeWithoutStatus2("asdasdas", "I believe this is enough!"));
        assertEquals(testList.getValue().size(), 2);


        LiveData<List<JokeWithoutStatus2>> testList2 = repo.getJokes(2);

        assertNotNull(testList);
        assertNotNull(testList.getValue());

        assertEquals(testList.getValue().get(0), new JokeWithoutStatus2("asasadasdas", "I believe thaaaaaaaaais is enough!"));
        assertEquals(testList.getValue().size(), 4);


        LiveData<List<JokeWithoutStatus2>> testList6 = repo.getJokes(6);

        assertNotNull(testList);
        assertNotNull(testList.getValue());
        assertEquals(testList.getValue().size(), 0);

    }

}