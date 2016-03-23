/**
 * Created by jasonzhang on 2/25/16.
 */
// import org.json jar file
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
// import other necessary libraries
import java.util.*;
import java.text.*;
import java.sql.*;
import java.sql.Date;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
// create constructor class for business
class Business {
    String business_id;
    String name;
    String city;
    String state;
    double stars;
    Business(String i, String n, String c, String s, double r) {
        business_id = i;
        name = n;
        city = c;
        state = s;
        stars = r;
    }
}
// create constructor class for category
class Category {
    String business_id;
    String name;
    Category(String i, String n) {
        business_id = i;
        name = n;
    }
}
// create constructor class for subcategory
class SubCategory {
    String business_id;
    String name;
    SubCategory(String i, String n) {
        business_id = i;
        name = n;
    }
}
// create constructor class for review
class Review {
    String review_id;
    String business_id;
    String review_date;
    int stars;
    int votes;
    Review(String ri, String bi, String d, int s, int v) {
        review_id = ri;
        business_id = bi;
        review_date = d;
        stars = s;
        votes = v;
    }
}
// create constructor class for checkin
class Checkin {
    String business_id;
    String time_slot;
    int count;
    Checkin(String i, String t, int c) {
        business_id = i;
        time_slot = t;
        count = c;
    }
}
// create constructor class for user
class User {
    String user_id;
    String yelping_since;
    String name;
    int review_count;
    int friend_count;
    float average_stars;
    User(String i, String s, String n, int r, int f, float a) {
        user_id = i;
        yelping_since = s;
        name = n;
        review_count = r;
        friend_count = f;
        average_stars = a;
    }
}
// create constructor class for parsing business, category and subcategory
class Tuple<X, Y, Z> {
    final X x;
    final Y y;
    final Z z;
    Tuple(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
// create class populate
public class populate {
    // database user information
    static final String DB_USER = "";
    static final String DB_PASS = "";
    // JDBC driver information
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    // parsing yelp_business.json into three tables, business, category, subcategory
    public static Tuple<ArrayList<Business>, ArrayList<Category>, ArrayList<SubCategory>> parseBusiness(File file) {
        ArrayList<Business> businesses = new ArrayList<Business>();
        ArrayList<Category> categories = new ArrayList<Category>();
        ArrayList<SubCategory> subCategories = new ArrayList<SubCategory>();
        try {
            System.out.println(file.getName() + ": is parsing...");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                String business_id = obj.getString("business_id");
                String name = obj.getString("name");
                String city = obj.getString("city");
                String state = obj.getString("state");
                double stars = obj.getDouble("stars");
                businesses.add(new Business(business_id,name,city,state,stars));
                JSONArray categoryArray = obj.getJSONArray("categories");
                for(int i = 0 ; i < categoryArray.length(); i++) {
                    String categoryName = categoryArray.getString(i);
                    if (categoryName.equals("Active Life") || categoryName.equals("Arts & Entertainment") || categoryName.equals("Automotive") || categoryName.equals("Car Rental") || categoryName.equals("Cafes") || categoryName.equals("Beauty & Spas") || categoryName.equals("Convenience Stores") || categoryName.equals("Dentists") || categoryName.equals("Doctors") || categoryName.equals("Drugstores") || categoryName.equals("Department Stores") || categoryName.equals("Education") || categoryName.equals("Event Planning & Services") || categoryName.equals("Flowers & Gifts") || categoryName.equals("Food") || categoryName.equals("Health & Medical") || categoryName.equals("Home Services") || categoryName.equals("Home & Garden") || categoryName.equals("Hospitals") || categoryName.equals("Hotels & Travel") || categoryName.equals("Hardware Stores") || categoryName.equals("Grocery") || categoryName.equals("Medical Centers") || categoryName.equals("Nurseries & Gardening") || categoryName.equals("Nightlife") || categoryName.equals("Restaurants") || categoryName.equals("Shopping") || categoryName.equals("Transportation")) {
                        categories.add(new Category(business_id,categoryName));
                    } else {
                        subCategories.add(new SubCategory(business_id,categoryName));
                    }
                }
            }
            System.out.println(file.getName() + " " + businesses.size() + " businesses parsed!");
            System.out.println(file.getName() + " " + categories.size() + " categories parsed!");
            System.out.println(file.getName() + " " + subCategories.size() + " subCategories parsed!");
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Tuple<ArrayList<Business>, ArrayList<Category>, ArrayList<SubCategory>>(businesses, categories, subCategories);
    }
    // parse yelp_review.json into review table
    public static ArrayList<Review> parseReview(File file) {
        ArrayList<Review> reviews = new ArrayList<Review>();
        try {
            System.out.println(file.getName() + " is parsing data...");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                String review_id = obj.getString("review_id");
                String business_id = obj.getString("business_id");
                String review_date = obj.getString("date");
                int stars = obj.getInt("stars");
                JSONObject review_votes = obj.getJSONObject("votes");
                int votes = review_votes.getInt("useful") + review_votes.getInt("funny") + review_votes.getInt("cool");
                reviews.add(new Review(review_id, business_id, review_date, stars, votes));
            }
            System.out.println(file.getName() + " " + reviews.size() + " reviews parsed!");
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    // parse yelp_checkin.json into checkin table
    public static ArrayList<Checkin> parseCheckin(File file) {
        ArrayList<Checkin> checkins = new ArrayList<Checkin>();
        try {
            System.out.println(file.getName() + " is parsing data...");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                String business_id = obj.getString("business_id");
                JSONObject time_slots = obj.getJSONObject("checkin_info");
                for(int i = 0; i < time_slots.names().length(); i++) {
                    String time_slot = time_slots.names().getString(i);
                    int count = time_slots.getInt(time_slots.names().getString(i));
                    checkins.add(new Checkin(business_id, time_slot, count));
                }
            }
            System.out.println(file.getName() + " - " + checkins.size() + " checkins parsed!");
            br.close();
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return checkins;
    }
    // parse yelp_user.json into user table
    public static ArrayList<User> parseUser(File file) {
        ArrayList<User> users = new ArrayList<User>();
        try {
            System.out.println(file.getName() + " is parsing data...");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                String user_id = obj.getString("user_id");
                String yelping_since = obj.getString("yelping_since");
                String name = obj.getString("name");
                int review_count = obj.getInt("review_count");
                JSONArray friends = obj.getJSONArray("friends");
                int friend_count = friends.length();
                float average_stars = (float) obj.getDouble("average_stars");
                users.add(new User(user_id,yelping_since,name,review_count,friend_count,average_stars));
            }
            System.out.println(file.getName() + " - " + users.size() + " users parsed!");
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
    // pass json file into main
    public static void main(String[] argv) {
        // arg[0] yelp_business.json
        ArrayList<Business> businesses = new ArrayList<Business>();
        ArrayList<Category> categories = new ArrayList<Category>();
        ArrayList<SubCategory> subCategories = new ArrayList<SubCategory>();
        // arg[1] yelp_review.json
        ArrayList<Review> reviews = new ArrayList<Review>();
        // arg[2] yelp_checkin.json
        ArrayList<Checkin> checkins = new ArrayList<Checkin>();
        // arg[3] yelp_user.json
        ArrayList<User> users = new ArrayList<User>();
        System.out.println("Preparing data...");
        if (argv.length >= 4) {
            File business = new File(argv[0]);
            Tuple<ArrayList<Business>,ArrayList<Category>,ArrayList<SubCategory>> tuple = parseBusiness(business);
            businesses = tuple.x;
            categories = tuple.y;
            subCategories = tuple.z;
            File review = new File(argv[1]);
            reviews = parseReview(review);
            File checkin = new File(argv[2]);
            checkins = parseCheckin(checkin);
            File user = new File(argv[3]);
            users = parseUser(user);
        } else {
            System.out.println("Error: invalid input files!");
            return;
        }
        // check JDBC
        System.out.println("Message: it is testing JDBC...");
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: failed to locate JDBC");
            e.printStackTrace();
            return;
        }
        System.out.println("Message: JDBC has been successfully registered!");
        // check database connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null) {
                System.out.println("Message: it successfully established database connection!");
            } else {
                conn.close();
                System.out.println("Error: fail to build a database connection! Please check your user name and password!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error: fail to build a database connection! Please check your user name and password");
            e.printStackTrace();
            return;
        }
        // clear data in the table
        Statement stmt = null;
        System.out.println("Message: clear table...");
        try {
            System.out.println("Message: checkin table is being clearing...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM CHECKIN";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error: fail to clear checkin table!");
            e.printStackTrace();
        }
        System.out.println("Message: checkin table is cleared!");
        try {
            System.out.println("Message: review table is being clearing...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM REVIEW";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error: fail to clear review table!");
            e.printStackTrace();
        }
        System.out.println("Message: checkin table is cleared!");
        try {
            System.out.println("Message: subcategory table is being clearing...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM SUBCATEGORY";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error: fail to clear subcategory table!");
            e.printStackTrace();
        }
        System.out.println("Message: subcategory table is cleared!");
        try {
            System.out.println("Message: category table is being clearing...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM CATEGORY";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error: fail to clear category table!");
            e.printStackTrace();
        }
        System.out.println("Message: category table is cleared!");
        try {
            System.out.println("Message: business table is being clearing...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM BUSINESS";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error: fail to clear business table!");
            e.printStackTrace();
        }
        System.out.println("Message: business table is cleared!");
        try {
            System.out.println("Message: user table is being clearing...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM YELP_USER";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error: fail to clear user table!");
            e.printStackTrace();
        }
        System.out.println("Message: user table is cleared!");
        // insert data into table
        PreparedStatement pstmt = null;
        try {
            System.out.println("Message: inserting data into business table...");
            String sql =    "INSERT INTO BUSINESS (business_id, name, city, state, stars) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (Business b : businesses) {
                pstmt.setString(1, b.business_id);
                pstmt.setString(2, b.name);
                pstmt.setString(3, b.city);
                pstmt.setString(4, b.state);
                pstmt.setDouble(5, b.stars);
                pstmt.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error: fail to insert data into business table!");
            e.printStackTrace();
        }
        System.out.println("Message: finished business table");
        try {
            System.out.println("Message: inserting data into category table...");
            String sql = "INSERT INTO CATEGORY (business_id, name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (Category c : categories) {
                pstmt.setString(1, c.business_id);
                pstmt.setString(2, c.name);
                pstmt.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error: fail to insert data into category table!");
            e.printStackTrace();
        }
        System.out.println("Message: finish category table!");
        try {
            System.out.println("Message: inserting data into subcategory table...");
            String sql = "INSERT INTO SUBCATEGORY (business_id, name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (SubCategory s : subCategories) {
                pstmt.setString(1, s.business_id);
                pstmt.setString(2, s.name);
                pstmt.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error: fail to insert data into subcategory!");
            e.printStackTrace();
        }
        System.out.println("Message: finished subcategory table!");
        try {
            System.out.println("Message: inserting data into review table...");
            String sql =    "INSERT INTO REVIEW (review_id, business_id, review_date, stars, votes) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (Review r : reviews) {
                pstmt.setString(1, r.review_id);
                pstmt.setString(2, r.business_id);
                pstmt.setString(3, r.review_date);
                pstmt.setInt(4, r.stars);
                pstmt.setInt(5, r.votes);
                pstmt.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error: fail to insert data inot review table!");
            e.printStackTrace();
        }
        System.out.println("Message: finished review table!");
        try {
            System.out.println("Message: inserting data into checkin table...");
            String sql = "INSERT INTO CHECKIN (business_id, time_slot, count) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (Checkin c : checkins) {
                pstmt.setString(1, c.business_id);
                pstmt.setString(2, c.time_slot);
                pstmt.setInt(3, c.count);
                pstmt.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error: fail to insert data inot checkin table!");
            e.printStackTrace();
        }
        System.out.println("Message: finished checkin table!");
        try {
            System.out.println("Message: insering data into user table...");
            String sql = "INSERT INTO YELP_USER (user_id, yelping_since, name, review_count, friend_count, average_stars) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (User u : users) {
                pstmt.setString(1, u.user_id);
                pstmt.setString(2, u.yelping_since);
                pstmt.setString(3, u.name);
                pstmt.setInt(4, u.review_count);
                pstmt.setInt(5, u.friend_count);
                pstmt.setFloat(6, u.average_stars);
                pstmt.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("Error: fail to insert data into user table!");
            e.printStackTrace();
        }
        System.out.println("Message: finished user table!");
        // close the connection to the database
        try {
            conn.close();
            System.out.println("Message: finished all tables and closed the connection to database...");
            return;
        } catch(SQLException e) {
            System.out.println("Error: failed to disconnect with database!");
            e.printStackTrace();
            return;
        }
    }
}