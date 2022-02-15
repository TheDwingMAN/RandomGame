package game.entity;

import game.GamePanel;
import game.map.Tile;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean hasCollision(Entity entity,Direction direction) {

        int entityLeftWorldX = entity.getX() + entity.getHitbox().x;
        int entityRightWorldX = entity.getX() + (entity.getHitbox().x + entity.getHitbox().width) * entity.getTexture().getStandingUp().getScale();
        int entityTopWorldY = entity.getY() + entity.getHitbox().y;
        int entityBottomWorldY = entity.getY() + (entity.getHitbox().y + entity.getHitbox().height) * entity.getTexture().getStandingUp().getScale();

        int entityLeftCol = entityLeftWorldX/gp.getTileSize();
        int entityRightCol = entityRightWorldX/gp.getTileSize();
        int entityTopRow = entityTopWorldY/gp.getTileSize();
        int entityBottomRow = entityBottomWorldY/gp.getTileSize();
        Tile tile1,tile2;
        //if(player X + 7)
        switch (direction) {
            case UP:

                entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.getTileSize();
                tile1 = gp.getTileManager().getTileAt(entityTopRow,entityLeftCol);
                tile2 = gp.getTileManager().getTileAt(entityTopRow,entityRightCol);
                return tile1.isCollision() || tile2.isCollision();

            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gp.getTileSize();
                tile1 = gp.getTileManager().getTileAt(entityBottomRow,entityLeftCol);
                tile2 = gp.getTileManager().getTileAt(entityBottomRow,entityRightCol);
                return tile1.isCollision() || tile2.isCollision();

            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gp.getTileSize();
                tile1 = gp.getTileManager().getTileAt(entityTopRow,entityLeftCol);
                tile2 = gp.getTileManager().getTileAt(entityBottomRow,entityLeftCol);
                return tile1.isCollision() || tile2.isCollision();

            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.getSpeed())/gp.getTileSize();
                tile1 = gp.getTileManager().getTileAt(entityTopRow,entityRightCol);
                tile2 = gp.getTileManager().getTileAt(entityBottomRow,entityRightCol);
                return tile1.isCollision() || tile2.isCollision();
        }

        return false;




    }
}
