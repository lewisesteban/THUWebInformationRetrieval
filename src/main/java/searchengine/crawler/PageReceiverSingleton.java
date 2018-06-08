package searchengine.crawler;

class PageReceiverSingleton {

    private static PageReceiver receiver;

    static void set(PageReceiver newReceiver) {
        receiver = newReceiver;
    }

    static PageReceiver get() {
        return receiver;
    }
}
