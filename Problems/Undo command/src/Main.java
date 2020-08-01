interface Movable {
    int getX();

    int getY();

    void setX(int newX);

    void setY(int newY);
}

interface Storable {
    int getInventoryLength();

    String getInventoryItem(int index);

    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();

    void undo();
}

class CommandMove implements Command {
    Movable entity;
    int xMovement;
    int yMovement;

    @Override
    public void execute() {
        entity.setX(entity.getX() + xMovement);
        entity.setY(entity.getY() + yMovement);
    }

    @Override
    public void undo() {
        entity.setX(entity.getX() - xMovement);
        entity.setY(entity.getY() - yMovement);
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;
    Integer lastPutIndex = null;

    @Override
    public void execute() {
        lastPutIndex = freeSlot();
        if (lastPutIndex != null) {
            entity.setInventoryItem(lastPutIndex, item);
        }
    }

    @Override
    public void undo() {
        if (lastPutIndex != null) {
            entity.setInventoryItem(lastPutIndex, null);
        }
    }

    private Integer freeSlot() {
        for (int i = 0; i < entity.getInventoryLength(); i++) {
            if (entity.getInventoryItem(i) == null) {
                return i;
            }
        }
        return null;
    }
}
