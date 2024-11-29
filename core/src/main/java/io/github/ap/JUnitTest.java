//package io.github.ap;
//
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class JUnitTest{
//
//    MainMenu mainMenu;
//
//    @BeforeEach
//    public void setUp(){
//        mainMenu = new MainMenu(new SpriteBatch());
//    }
//
//    @Test
//    void loginTest() throws IOException{
//        assertEquals(0, mainMenu.checkLogin(), "working properly");
//    }
//
//    @Test
//    void registerTest() throws IOException{
//        assertNotEquals(-1, mainMenu.checkLogin(), "working properly");
//    }
//
//}
