package com.example.demo;

import com.example.demo.dao.CollectMapper;
import com.example.demo.dao.ListSongMapper;
import com.example.demo.dao.RankMapper;
import com.example.demo.dao.SongMapper;
import com.example.demo.domain.*;
import com.example.demo.recommend.dto.ProductDTO;
import com.example.demo.recommend.dto.RelateDTO;
import com.example.demo.service.RecommendSongListService;
import com.example.demo.service.impl.ConsumerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicApplicationTests {

    @Autowired
//    private SongServiceImpl songService;
//    private SingerServiceImpl singerService;
//    private SongListServiceImpl songListService;
    private ConsumerServiceImpl consumerService;
//    private RankServiceImpl rankService;



//    @Test
//    public void contextLoads() {
//
//    }

//    @Test
//    public void testRank(){
//        Rank rank = new Rank();
//        rank.setConsumerId(2L);
//        rank.setScore(3);
//        rank.setSongListId(2L);
//        rankService.insert(rank);
//        System.out.println("歌单均分为"+rankService.selectAverScore(2L));
//    }

//歌曲
//    @Test
//    public void songTest(){
//            Song song = new Song();
//            song.setName("Sanna Nielsen-Undo");
//            song.setPic("/img/songPic/1775711278864263.jpg");
//            song.setSingerId(42);
//            song.setCreateTime(new Date());
//            song.setUpdateTime(new Date());
//            song.setIntroduction("Undo");
//            song.setLyric("[00:00:00]暂无歌词");
//            song.setUrl("/song/Sanna Nielsen-Undo.mp3");
//            songService.addSong(song);
//    }


//    //歌手
//    @Test
//    public void singerTest(){
//        Singer singer = new Singer();
//        singer.setName("Álvaro Soler");
//        singer.setSex(new Byte("1"));
//        singer.setPic("/img/singerPic/soler.jpg");
//        singer.setBirth(new Date());
//        singer.setLocation("西班牙");
//        singer.setIntroduction("全名是Álvaro Tauchert Soler，是一位新晋西班牙歌手，流行音乐作曲家。出生于1991年，西班牙巴塞罗纳。");
//        singerService.addSinger(singer);
//    }
//    @Test
//    public void singerTest2()
//    {
//        System.out.println(singerService.allSinger());
//    }

    //歌单
//    @Test
//    public void songListTest(){
//
//        SongList songList = new SongList();
//        songList.setTitle("国风传统器乐赏~~♪");
//        songList.setPic("/img/songListPic/19169985230816413.jpg");
//        songList.setIntroduction(" 都是自己很喜欢的吉他指弹");
//        songList.setStyle("乐器");
//        songListService.addSongList(songList);
//    }
//    @Test
//    public void songListTest2()
//    {
//        System.out.println(songListService.allSongList());
//    }

//用户
@Test
public void consumerTest(){

    Consumer consumer = new Consumer();
    consumer.setUsername("test");
    consumer.setPassword("123");
    consumer.setSex(new Byte("0"));
    consumer.setPhoneNum("15666412237");
    consumer.setEmail("1239679@qq.com");
    consumer.setBirth(new Date());
    consumer.setIntroduction("");
    consumer.setLocation("");
    consumer.setAvator("/img/user.jpg");
    consumer.setCreateTime(new Date());
    consumer.setUpdateTime(new Date());
    consumerService.addUser(consumer);
}
//    @Test
//    public void consumerTest2()
//    {
//        System.out.println(consumerService.allUser());
//    }



    @Autowired
    private RankMapper rankMapper;

    @Autowired
    private CollectMapper collectMapper;

    @Test
    public void batchInsertCollect(){
        for(int i = 0; i < 500; i++){
            Collect collect = new Collect();
            collect.setUserId(new Random().nextInt(20) + 1);
            collect.setType((byte)0);
            collect.setSongId(new Random().nextInt(50) + 1);
            collect.setCreateTime(new Date());
            collectMapper.insertSelective(collect);
        }
/*        Collect collect = new Collect();
        collect.setUserId(new Random().nextInt(50) + 1);
        collect.setType((byte)0);
        collect.setSongId(new Random().nextInt(111) + 1);
        collect.setCreateTime(new Date());
        collectMapper.insertSelective(collect);*/
    }

    @Test
    public void batchInsertRank(){
        for(int i = 1; i < 200; i++){
            Rank rank = new Rank();
            rank.setConsumerId((long)new Random().nextInt(50) + 1);
            rank.setSongListId((long)new Random().nextInt(84) + 1);
            rank.setScore((int)(Math.random()*10+1));
            rankMapper.insertSelective(rank);
        }
/*        Rank rank = new Rank();
        rank.setConsumerId((long)new Random().nextInt(50) + 1);
        rank.setSongListId((long)new Random().nextInt(84) + 1);
        rank.setScore((int)(Math.random()*10+1));
        System.out.println(rank);
        rankMapper.insert(rank);*/

    }

    @Autowired
    private RecommendSongListService recommendSongListService;

    @Test
    public void getRankDataTest(){
        List<RelateDTO> rankData = recommendSongListService.getRankData();
        for (RelateDTO rankDatum : rankData) {
            System.out.println(rankDatum);
        }
    }
    @Test
    public void testGetSongListData() {
        List<ProductDTO> songListData = recommendSongListService.getSongListData();
        for (ProductDTO songListDatum : songListData) {
            System.out.println(songListDatum);
        }
    }

    @Test
    public void testRecommendSongList() {
        List<SongList> songLists = recommendSongListService.recommendSongListByRank(5);
        System.out.println("推荐歌单数量=" + songLists.size());
        for (SongList songList : songLists) {
            System.out.println(songList);
        }
    }

    @Test
    public void testGetSongData(){
        List<ProductDTO> songData = recommendSongListService.getSongData();
        System.out.println("-----------songs=" + songData.size());
    }

    @Test
    public void testGetCollect(){
        List<RelateDTO> collectData = recommendSongListService.getCollectData();
        System.out.println("collectData = " + collectData.size());
    }
    @Autowired
    private ListSongMapper listSongMapper;
    @Test
    public void testRecommendSongs(){
        List<Song> songs = recommendSongListService.recommendSongs(1);
        System.out.println("recommend songs = " + songs.size() );
        for (Song song : songs) {
            System.out.println("songId = " + song.getId());
        }

        List<ListSong> listSongLists = listSongMapper.allListSong();
        //System.out.println("listSongLists = " + listSongLists.size());
        List<ListSong> listSongList1 = new ArrayList<>();
        List<Integer> songIds = songs.stream().map(e -> e.getId()).collect(Collectors.toList());
        List<ListSong> listSongs = listSongLists.stream().filter(e -> songIds.contains(e.getSongId())).collect(Collectors.toList());
/*        for (Song song : songs) {
            for (ListSong listSongList : listSongLists) {
                if(song.getId() == listSongList.getSongId()){
                    listSongList1.add(listSongList);
                }
            }
        }
        System.out.println(listSongList1.size());*/
        System.out.println(listSongs.size());
    }

    @Test
    public void testRecommendSongListByCollect(){
        List<SongList> songLists = recommendSongListService.recommendSongListByCollect(3);
        System.out.println("推荐的歌单数量 = " + songLists.size());
        for (SongList songList : songLists) {
            System.out.println("songListId = " + songList.getId());
        }
    }
    @Autowired
    private SongMapper songMapper;
    @Test
    public void testSongMapper(){
        Song song = songMapper.selectByPrimaryKey(1);
        System.out.println(song);
    }
}


