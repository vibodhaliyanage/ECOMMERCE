package lk.astrea.e_commerce.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import lk.astrea.e_commerce.R;
import lk.astrea.e_commerce.fragment.CartFragment;
import lk.astrea.e_commerce.fragment.CategoryFragment;
import lk.astrea.e_commerce.fragment.HomeFragment;
import lk.astrea.e_commerce.fragment.MailsFragment;
import lk.astrea.e_commerce.fragment.OrderFragment;
import lk.astrea.e_commerce.fragment.ProfileFragment;
import lk.astrea.e_commerce.fragment.SettingFragment;
import lk.astrea.e_commerce.fragment.WishListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener {

    private DrawerLayout drawerLayout;
    private MaterialToolbar toolbar;

    private NavigationView navigationView;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.tool_bar);
        navigationView = findViewById(R.id.side_navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    finish();
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnItemSelectedListener(this);

        if (savedInstanceState==null){

        loadFragment(new HomeFragment());
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        int itemId = menuItem.getItemId();

        navigationView.setCheckedItem(-1);


        if (itemId == R.id.side_nav_home | itemId == R.id.bottom_nav_home) {
            loadFragment(new HomeFragment());
           navigationView.setCheckedItem(R.id.side_nav_home);
           bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);

        } else if (itemId == R.id.side_nav_profile | itemId == R.id.bottom_nav_profile) {

            loadFragment(new ProfileFragment());
            navigationView.setCheckedItem(R.id.side_nav_profile);
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_profile);
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();

        } else if (itemId == R.id.side_nav_order) {

            loadFragment(new OrderFragment());
            navigationView.setCheckedItem(R.id.side_nav_order);
            Toast.makeText(this, "Order", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.side_nav_wishlist) {

            loadFragment(new WishListFragment());
            Toast.makeText(this, "Wishlist", Toast.LENGTH_SHORT).show();
            navigationView.setCheckedItem(R.id.side_nav_wishlist);
        } else if (itemId == R.id.side_nav_cart | itemId == R.id.bottom_nav_cart) {

            loadFragment(new CartFragment());
            Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
            navigationView.setCheckedItem(R.id.side_nav_cart);
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_cart);
        } else if (itemId == R.id.side_nav_message) {

            loadFragment(new MailsFragment());
            Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
            navigationView.setCheckedItem(R.id.side_nav_message);

        } else if (itemId == R.id.side_nav_settings) {
            loadFragment(new SettingFragment());
            navigationView.setCheckedItem(R.id.side_nav_settings);
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();

        } else if (itemId == R.id.side_nav_login) {

            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.side_nav_logOut) {

            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();

        } else if (itemId == R.id.bottom_nav_category) {
            loadFragment(new CategoryFragment());
        }

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
            return true;
        }

        private void loadFragment(Fragment fragment){

      FragmentManager fragmentManager = getSupportFragmentManager();
  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
  fragmentTransaction.replace(R.id.fragmentContainer,fragment);
  fragmentTransaction.commit();
        }

    }