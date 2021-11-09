package com.mygdx.game.listeners;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.*;
import com.mygdx.game.ACLGame;
import com.mygdx.game.components.AttackerComponent;
import com.mygdx.game.components.DirectionComponent;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.systems.AttackSystem;
import com.mygdx.game.systems.HeroSystem;

public class ACLGameListener extends InputAdapter  {

    private Engine engine;
    private ACLGame game;
    public ACLGameListener(GameScreen gameScreen){
        this.engine = gameScreen.engine;
        this.game=gameScreen.game;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.Z:
            case Input.Keys.W:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.UP);
                return true;
            case Input.Keys.S:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.DOWN);
                return true;
            case Input.Keys.D:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.RIGHT);
                return true;
            case Input.Keys.Q:
            case Input.Keys.A:
                engine.getSystem(HeroSystem.class).setHeroDirection(DirectionComponent.LEFT);
                return true;
            case Input.Keys.J:
                engine.getSystem(AttackSystem.class).attack();
        }
        return false;
    }


}
