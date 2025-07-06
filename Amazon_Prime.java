import java.util.*;

class Amazonprime {
    private int primeId;
    private String userName;
    private int subscribedPackage;
    private String showStreaming;
    private int views;

    public Amazonprime(int primeId, String userName, int subscribedPackage, String showStreaming, int views) {
        this.primeId = primeId;
        this.userName = userName;
        this.subscribedPackage = subscribedPackage;
        this.showStreaming = showStreaming;
        this.views = views;
    }

    // Getters
    public int getPrimeId() {
        return primeId;
    }

    public String getUserName() {
        return userName;
    }

    public int getSubscribedPackage() {
        return subscribedPackage;
    }

    public String getShowStreaming() {
        return showStreaming;
    }

    public int getViews() {
        return views;
    }

    // Setters
    public void setPrimeId(int primeId) {
        this.primeId = primeId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSubscribedPackage(int subscribedPackage) {
        this.subscribedPackage = subscribedPackage;
    }

    public void setShowStreaming(String showStreaming) {
        this.showStreaming = showStreaming;
    }

    public void setViews(int views) {
        this.views = views;
    }
}

public class Solution {

    // Method 1
    public static int findRemainingSubscriptionDays(Amazonprime[] prime, int days, int primeId) {
        for (Amazonprime ap : prime) {
            if (ap.getPrimeId() == primeId) {
                return ap.getSubscribedPackage() - days;
            }
        }
        return 0;
    }

    // Method 2
    public static Amazonprime[] findDetailsForGivenShow(Amazonprime[] prime, String show) {
        List<Amazonprime> filtered = new ArrayList<>();
        for (Amazonprime ap : prime) {
            if (ap.getShowStreaming().equalsIgnoreCase(show)) {
                filtered.add(ap);
            }
        }

        if (filtered.isEmpty()) {
            return null;
        }

        Collections.sort(filtered, new Comparator<Amazonprime>() {
            public int compare(Amazonprime a1, Amazonprime a2) {
                return Integer.compare(a1.getViews(), a2.getViews());
            }
        });

        return filtered.toArray(new Amazonprime[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Amazonprime[] arr = new Amazonprime[4];

        for (int i = 0; i < 4; i++) {
            int primeId = Integer.parseInt(sc.nextLine());
            String userName = sc.nextLine();
            int subscribedPackage = Integer.parseInt(sc.nextLine());
            String showStreaming = sc.nextLine();
            int views = Integer.parseInt(sc.nextLine());
            arr[i] = new Amazonprime(primeId, userName, subscribedPackage, showStreaming, views);
        }

        int days = Integer.parseInt(sc.nextLine());
        int searchPrimeId = Integer.parseInt(sc.nextLine());
        String searchShow = sc.nextLine();

        // Print remaining subscription days
        int remainingDays = findRemainingSubscriptionDays(arr, days, searchPrimeId);
        if (remainingDays > 0) {
            System.out.println(remainingDays);
        } else {
            System.out.println("Its time to recharge your Prime Account");
        }

        // Print details for show
        Amazonprime[] result = findDetailsForGivenShow(arr, searchShow);
        if (result == null) {
            System.out.println("No such shows available");
        } else {
            for (Amazonprime ap : result) {
                System.out.println(ap.getPrimeId() + "$" + ap.getUserName() + "$" + ap.getViews());
            }
        }
    }
}
