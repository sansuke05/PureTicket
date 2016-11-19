package soramitsu.io.katyusha.view;

public interface Navigator {
    void gotoTop();
    void gotoTransaction();
    void gotoConfirmTransaction();
    void gotoReceive();
    void gotoRightsList();
    void gotoBadgeList();
    void gotoTransactionHistory();
}