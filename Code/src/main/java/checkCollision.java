/**
 * class to check collisions between wall and characters
 */
public class checkCollision {
    public GameFrame check_frame; //frame used to check collisions
    public checkCollision(GameFrame check_frame) {
        this.check_frame = check_frame;
    }

    /**
     * check for intersection between character and wall
     * set boolean collisionArea accordingly
     * @param obj
     */
    public void checkTile(DynamicCharacter obj){
        int scaledCellSize = check_frame.getCellSize()/2;

        //character position variables
        int charcTop = obj.y+obj.hitAreaStatic.y;
        int charcBottom = obj.y+obj.hitAreaStatic.y + obj.hitAreaStatic.height;
        int charcLeft = obj.x+obj.hitAreaStatic.x;
        int charcRight = obj.x+obj.hitAreaStatic.x + obj.hitAreaStatic.width;

        //Wall index variables
        int charcTopRow = charcTop/scaledCellSize;
        int charcBottomRow = charcBottom/scaledCellSize;
        int charcLeftCol = charcLeft/scaledCellSize;
        int charcRightCol = charcRight/scaledCellSize;

        int tile1,tile2;
        int[][] board = check_frame.tileFrame.getBoard(check_frame.settings.getGameLevel());

        switch(obj.direction){

            case "up":
                charcTopRow = (charcTop - obj.speed)/scaledCellSize;
                if (charcTopRow < 0 || charcTopRow > 23 || charcLeftCol < 0 || charcLeftCol > 31 || charcRightCol < 0 || charcRightCol > 31){
                    obj.collision = true;
                } else {
                    setTileCollisions(charcTopRow,charcLeftCol,charcTopRow,charcRightCol,obj);
                }
                break;
            case "down":
                charcBottomRow = (charcBottom + obj.speed)/scaledCellSize;
                if (charcBottomRow < 0 || charcBottomRow > 23 || charcLeftCol < 0 || charcLeftCol > 31 || charcRightCol < 0 || charcRightCol > 31){
                    obj.collision = true;
                } else {
                    setTileCollisions(charcBottomRow,charcLeftCol,charcBottomRow,charcRightCol,obj);
                }
                break;
            case "right":
                charcRightCol = (charcRight - obj.speed)/scaledCellSize;
                if (charcBottomRow < 0 || charcBottomRow > 23 || charcTopRow < 0 || charcTopRow > 23 || charcRightCol < 0 || charcRightCol > 31){
                    obj.collision = true;
                } else {
                    setTileCollisions(charcTopRow,charcRightCol+1,charcBottomRow,charcRightCol+1,obj);
                }
                break;
            case "left":
                charcLeftCol = (charcLeft + obj.speed)/scaledCellSize;
                if (charcBottomRow < 0 || charcBottomRow > 23 || charcTopRow < 0 || charcTopRow > 23 || charcLeftCol < 0 || charcLeftCol > 31) {
                    obj.collision = true;
                }else {
                    setTileCollisions(charcTopRow,charcLeftCol,charcBottomRow,charcLeftCol,obj);
                }
                break;
            default:break;
        }
    }

    /**
     * check calculated index for collisions and set collision value accordingly
     * @param tile1x
     * @param tile1y
     * @param tile2x
     * @param tile2y
     * @param obj
     */
    private void setTileCollisions(int tile1x, int tile1y, int tile2x, int tile2y, DynamicCharacter obj){
        int tile1,tile2;
        int[][] board = check_frame.tileFrame.getBoard(check_frame.settings.getGameLevel());
        tile1 = board[tile1x][tile1y];
        tile2 = board[tile2x][tile2y];

        if(check_frame.tileFrame.tile[tile1].getCollision() || check_frame.tileFrame.tile[tile2].getCollision()) {
            obj.collision = true;
        }
    }
}
