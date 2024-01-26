public interface Action {

    ImageStore getImageStore();

    void executeAction(EventScheduler scheduler);

}
