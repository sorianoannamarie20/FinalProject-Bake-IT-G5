package com.example.bakeitapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.bakeitapp.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.mylayout.view.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    var adapter : FoodAdapter? = null
    var foodlist = ArrayList<Food>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
        foodlist.add(Food("Strawberry-Rhubarb Buckle", "Growing up in Wisconsin, we had a patch of rhubarb growing in our backyard, and I loved all of the delicious desserts my Mom would make when it was in season. This recipe is my spin on her strawberry-rhubarb cake. Serve warm with a scoop of vanilla ice cream or a dollop of sweetened whipped cream, if desired.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 40 mins\n" +
                "Total: 1 hr\n" +
                "Servings: 8\n" +
                "Yield: 8 servings\n" +
                "\n" +
                "INGREDIENTS\n" +
                "\n" +
                "STRAWBERRY-RHUBARB FILLING:\n" +
                "~ 1 ½ cups diced fresh rhubarb\n" +
                "~ 1 ½ cups diced fresh strawberries\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 tablespoon lemon juice\n" +
                "~ 3 tablespoons water, divided\n" +
                "~ 2 tablespoons cornstarch\n" +
                "\n" +
                "CAKE BATTER:\n" +
                "~ cooking spray\n" +
                "~ ½ cup butter, softened\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 large egg\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ ½ cup plain yogurt\n" +
                "~ 1 ½ cups all-purpose flour\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ½ teaspoon baking powder\n" +
                "~ ½ teaspoon salt\n" +
                "\n" +
                "TOPPING:\n" +
                "~ 3 tablespoons brown sugar\n" +
                "~ ¼ cup flour\n" +
                "~ 2 tablespoons butter\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine rhubarb, strawberries, 1/2 cup sugar, lemon juice and 1 tablespoon of water in a saucepan. Cook, covered, over medium-low heat, stirring occasionally until mixture begins to boil, about 5 minutes. Reduce heat to low.\n" +
                "\n" +
                "Step 2\n" +
                "In a small bowl, stir together remaining 2 tablespoons of water with cornstarch until cornstarch is dissolved. Add mixture to the saucepan and continue cooking fruit until mixture begins to thicken, about 2 minutes. Remove from stove and set aside to cool.\n" +
                "\n" +
                "Step 3\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Spray a 9-inch deep dish pie pan with non-stick cooking spray, and place on a baking sheet.\n" +
                "\n" +
                "Step 4\n" +
                "Beat 1/2 cup of butter and 1/2 cup of sugar with an electric mixer in a bowl until light and fluffy. Beat in egg, vanilla extract, and yogurt. Add flour, baking powder, baking soda, and salt; mix until just combined. Spread 2/3 of the batter into the prepared pan. Pour strawberry-rhubarb filling on top of the batter. Spread remaining 1/3 of the batter in small mounds atop the filling. Set aside.\n" +
                "\n" +
                "Step 5\n" +
                "Combine brown sugar, 1/4 cup flour, and 2 tablespoons of butter in a small bowl. Mix using a fork, a pastry blender, or your hands, until the mixture resembles coarse crumbs. Sprinkle on top of the batter and filling.\n" +
                "\n" +
                "Step 6\n" +
                "Bake in the preheated oven until the cake topping is nicely browned, about 35 minutes.", R.drawable.rhubarbbuckle))
        foodlist.add(Food("Peanut Butter Pie I", "This pie has a chocolate crumb crust and a creamy peanut butter filling. Garnish pie with grated chocolate or chocolate cookie crumbs, if desired.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 4 hrs\n" +
                "Total: 4 hrs 40 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 9-inch pie\n" +
                "\n" +
                "INGREDIENTS\n" +
                "1 ¼ cups chocolate cookie crumbs\n" +
                "¼ cup white sugar\n" +
                "¼ cup butter\n" +
                "1 (8 ounce) package cream cheese, softened\n" +
                "1 cup creamy peanut butter\n" +
                "1 cup white sugar\n" +
                "1 tablespoon unsalted butter, softened\n" +
                "1 teaspoon vanilla extract\n" +
                "1 cup heavy whipping cream\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 375 degrees F (190 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Combine 1 1/4 cup cookie crumbs, 1/4 cup sugar, and 1/4 cup butter; press into a 9-inch pie plate. Bake in preheated oven for 10 minutes. Cool on wire rack.\n" +
                "\n" +
                "Step 3\n" +
                "In a mixing bowl, beat cream cheese, peanut butter, 1 cup sugar, 1 tablespoon butter, and vanilla until smooth. Whip the cream, and fold into the peanut butter mixture.\n" +
                "\n" +
                "Step 4\n" +
                "Gently spoon filing into crust. Garnish pie with chocolate or cookie crumbs if desired. Refrigerate for several hours before serving.", R.drawable.peanutbutterpie))
        foodlist.add(Food("1 Bowl Chocolate Cake III", "This is a rich and moist chocolate cake. It only takes a few minutes to prepare the batter. Frost with your favorite chocolate frosting.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 30 mins\n" +
                "Additional: 10 mins\n" +
                "Total: 1 hr\n" +
                "Servings: 24\n" +
                "Yield: 2 - 9 inch round cake layers\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 cups white sugar\n" +
                "~ 1 ¾ cups all-purpose flour\n" +
                "~ ¾ cup unsweetened cocoa powder\n" +
                "~ 1 ½ teaspoons baking powder\n" +
                "~ 1 ½ teaspoons baking soda\n" +
                "~ 1 teaspoon salt\n" +
                "~ 2 eggs\n" +
                "~ 1 cup milk\n" +
                "~ ½ cup vegetable oil\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 1 cup boiling water\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour two nine inch round pans.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, stir together the sugar, flour, cocoa, baking powder, baking soda and salt. Add the eggs, milk, oil and vanilla, mix for 2 minutes on medium speed of mixer. Stir in the boiling water last. Batter will be thin. Pour evenly into the prepared pans.\n" +
                "\n" +
                "Step 3\n" +
                "Bake 30 to 35 minutes in the preheated oven, until the cake tests done with a toothpick. Cool in the pans for 10 minutes, then remove to a wire rack to cool completely.", R.drawable.bowlchocolatecakeiii))
        foodlist.add(Food("Best Lemon Bars", "Tart, rich and perfection, all rolled into one! Wow your friends with this simple recipe. Hint: No Substitutions!\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 40 mins\n" +
                "Total: 55 mins\n" +
                "Servings: 36\n" +
                "Yield: 1 - 9x13 inch pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup butter, softened\n" +
                "~ ½ cup white sugar\n" +
                "~ 2 cups all-purpose flour\n" +
                "~ 4 eggs\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ ¼ cup all-purpose flour\n" +
                "~ 2 lemons, juiced\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "In a medium bowl, blend together softened butter, 2 cups flour and 1/2 cup sugar. Press into the bottom of an ungreased 9x13 inch pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake for 15 to 20 minutes in the preheated oven, or until firm and golden. In another bowl, whisk together the remaining 1 1/2 cups sugar and 1/4 cup flour. Whisk in the eggs and lemon juice. Pour over the baked crust.\n" +
                "\n" +
                "Step 4\n" +
                "Bake for an additional 20 minutes in the preheated oven. The bars will firm up as they cool. For a festive tray, make another pan using limes instead of lemons and adding a drop of green food coloring to give a very pale green. After both pans have cooled, cut into uniform 2 inch squares and arrange in a checker board fashion.", R.drawable.bestlemonbars))
        foodlist.add(Food("Creamy Rice Pudding", "This is my mom's recipe for Rice Pudding. It's the best I've ever tasted and it gets rave reviews from everyone I serve it to. Sprinkle with nutmeg or cinnamon, if desired. For creamier pudding, use short or medium grain rice.\n" +
                "\n" +
                "Prep: 25 mins\n" +
                "Cook: 20 mins\n" +
                "Total: 45 mins\n" +
                "Servings: 4\n" +
                "Yield: 4 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ¾ cup uncooked white rice\n" +
                "~ 2 cups milk, divided\n" +
                "~ ⅓ cup white sugar\n" +
                "~ ¼ teaspoon salt\n" +
                "~ 1 egg, beaten\n" +
                "~ ⅔ cup golden raisins\n" +
                "~ 1 tablespoon butter\n" +
                "~ ½ teaspoon vanilla extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Bring 1 1/2 cups water to a boil in a saucepan; stir rice into boiling water. Reduce heat to low, cover, and simmer for 20 minutes.\n" +
                "\n" +
                "Step 2\n" +
                "In a clean saucepan, combine 1 1/2 cups cooked rice, 1 1/2 cups milk, sugar and salt. Cook over medium heat until thick and creamy, 15 to 20 minutes. Stir in remaining 1/2 cup milk, beaten egg, and raisins; cook 2 minutes more, stirring constantly. Remove from heat and stir in butter and vanilla.", R.drawable.creamyricepudding))
        foodlist.add(Food("Key Lime Pie VII", "This recipe uses condensed milk and sour cream. Fabulously easy. . . a summertime favorite! If you have time, a homemade graham cracker crust is better! Garnish with whipped cream and thin slices of lime if you like.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 8 mins\n" +
                "Additional: 32 mins\n" +
                "Total: 55 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 pie\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (9 inch) prepared graham cracker crust\n" +
                "~ 3 cups sweetened condensed milk\n" +
                "~ ½ cup sour cream\n" +
                "~ ¾ cup key lime juice\n" +
                "~ 1 tablespoon grated lime zest\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "In a medium bowl, combine condensed milk, sour cream, lime juice, and lime rind. Mix well and pour into graham cracker crust.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven for 5 to 8 minutes, until tiny pinhole bubbles burst on the surface of pie. DO NOT BROWN! Chill pie thoroughly before serving. Garnish with lime slices and whipped cream if desired.", R.drawable.keylimepievii))
        foodlist.add(Food("Spanish Flan", "Delicious Spanish flan, everyone will love it!\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 20 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 - 9 inch round\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup white sugar\n" +
                "~ 3 eggs\n" +
                "~ 1 (14 ounce) can sweetened condensed milk\n" +
                "~ 1 (12 fluid ounce) can evaporated milk\n" +
                "~ 1 tablespoon vanilla extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "In a medium saucepan over medium-low heat, melt sugar until liquefied and golden in color. Carefully pour hot syrup into a 9 inch round glass baking dish, turning the dish to evenly coat the bottom and sides. Set aside.\n" +
                "\n" +
                "Step 3\n" +
                "In a large bowl, beat eggs. Beat in condensed milk, evaporated milk and vanilla until smooth. Pour egg mixture into baking dish. Cover with aluminum foil.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in preheated oven 60 minutes. Let cool completely.\n" +
                "\n" +
                "Step 5\n" +
                "To serve, carefully invert on serving plate with edges when completely cool.", R.drawable.spanishflan))
        foodlist.add(Food("Tiramisu II", "Mascarpone custard layered with whipped cream and rum and coffee soaked ladyfingers.\n" +
                "\n" +
                "Prep: 35 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 4 hrs 15 mins\n" +
                "Total: 5 hrs\n" +
                "Servings: 12\n" +
                "Yield: 1 7x11-inch dish\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 6 egg yolks\n" +
                "~ ¾ cup white sugar\n" +
                "~ ⅔ cup milk\n" +
                "~ 1 ¼ cups heavy cream\n" +
                "~ ½ teaspoon vanilla extract\n" +
                "~ 1 pound mascarpone cheese, at room temperature\n" +
                "~ ¼ cup strong brewed coffee, at room temperature\n" +
                "~ 2 tablespoons rum\n" +
                "~ 2 (3 ounce) packages ladyfinger cookies\n" +
                "~ 1 tablespoon unsweetened cocoa powder\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a medium saucepan, whisk together egg yolks and sugar until well blended. Whisk in milk and cook over medium heat, stirring constantly, until mixture boils. Boil gently for 1 minute, remove from heat and allow to cool slightly. Cover tightly and chill in refrigerator 1 hour.\n" +
                "\n" +
                "Step 2\n" +
                "In a medium bowl, beat cream with vanilla until stiff peaks form.\n" +
                "\n" +
                "Step 3\n" +
                "Whisk mascarpone into yolk mixture until smooth.\n" +
                "\n" +
                "Step 4\n" +
                "In a small bowl, combine coffee and rum. Split ladyfingers in half lengthwise and drizzle with coffee mixture.\n" +
                "\n" +
                "Step 5\n" +
                "Arrange half of soaked ladyfingers in bottom of a 7x11 inch dish. Spread half of mascarpone mixture over ladyfingers, then half of whipped cream over that. Repeat layers and sprinkle with cocoa. Cover and refrigerate 4 to 6 hours, until set.", R.drawable.tiramisuii))
        foodlist.add(Food("Panna Cotta", "Delicious Italian dessert. You can serve it garnished with raspberries and mint leaves.\n" +
                "\n" +
                "Prep: 10 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 8 hrs 1 min\n" +
                "Total: 8 hrs 21 mins\n" +
                "Servings: 4\n" +
                "Yield: 4 individual servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 teaspoon unflavored gelatin\n" +
                "~ 1 tablespoon cool water\n" +
                "~ 1 ½ cups heavy whipping cream\n" +
                "~ ½ cup whole milk\n" +
                "~ ⅓ cup white sugar\n" +
                "~ 2 teaspoons grated lemon zest\n" +
                "~ ½ vanilla bean, split lengthwise\n" +
                "~ 6 tablespoons strawberry jam\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Sprinkle gelatin over water; let stand until dissolved, about 1 minute.\n" +
                "\n" +
                "Step 2\n" +
                "Place heavy cream, milk, sugar, lemon zest, and vanilla bean in a saucepan over medium heat; cook, stirring constantly, until mixture is simmering. Simmer gently, about 5 minutes. Remove from heat and discard vanilla bean.\n" +
                "\n" +
                "Step 3\n" +
                "Stir gelatin mixture into the hot cream mixture until dissolved. Stir in strawberry jam. Pour into small dessert cups, cover with plastic wrap, and refrigerate until set, 8 hours to overnight.", R.drawable.pannacottawithstrawberryjam ))
        foodlist.add(Food("Capirotada a la Antigua", "Capirotada is a traditional Mexican dessert, similar to what we know of as a bread pudding. In this variation, a baguette is sliced and toasted then layered with fruit in a dish and drizzled with a spiced fruit syrup. This dish is often eaten in Mexico around Easter time and carries a rich symbolism to the Passion of Christ.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 20 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 1 hr 40 mins\n" +
                "Servings: 12\n" +
                "Yield: 12 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 guava - peeled, halved, and seeded\n" +
                "~ 1 cup chopped piloncillo (Mexican brown sugar cones)\n" +
                "~ peel of 1/2 orange\n" +
                "~ 2 cinnamon sticks\n" +
                "~ 5 whole cloves\n" +
                "~ 5 whole allspice berries\n" +
                "~ 1 ¾ cups water\n" +
                "~ ¾ cup lard\n" +
                "~ 1 baguette, cut into 1/2-inch slices\n" +
                "~ ½ cup roasted peanuts\n" +
                "~ ½ cup raisins\n" +
                "~ ½ cup chopped pecans\n" +
                "~ ½ cup candied pineapple (Optional)\n" +
                "~ 3 ounces crumbled cotija cheese\n" +
                "~ 1 tablespoon multi-colored sprinkles, or to taste\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine guava, piloncillo, orange peel, cinnamon sticks, cloves, and allspice in a saucepan; add water. Bring liquid to a boil; reduce heat and simmer until a syrup forms, about 10 minutes. Strain syrup into a bowl.\n" +
                "\n" +
                "Step 2\n" +
                "Melt lard in a skillet over medium-high heat; fry baguette slices, working in batches, until golden brown, about 2 minutes per side. Drain on a paper towel-lined plate.\n" +
                "\n" +
                "Step 3\n" +
                "Arrange a layer of fried bread in a baking dish; top with half of the raisins, half of the pecans, half of the pineapple, and half of the cotija cheese. Drizzle half of the syrup over the top. Repeat layering with remaining fried bread, raisins, pineapple, cotija cheese, and syrup. Top with sprinkles. Let stand until bread has absorbed the syrup, 1 to 2 hours.", R.drawable.capirotadaalaantiguamexicanbreadpudding))
        foodlist.add(Food("Real Strawberry Frosting", "I wanted REAL strawberry frosting for my Real Strawberry Cupcakes and could not find an acceptable recipe. I wanted an intense strawberry taste without using an extract or gelatin. I came up with this recipe using a strawberry puree reduction that is truly the strawberry-est. If you love strawberries, you'll LOVE THIS!!!\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 20 mins\n" +
                "Total: 40 mins\n" +
                "Servings: 18\n" +
                "Yield: 18 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup fresh strawberries\n" +
                "~ 1 cup butter\n" +
                "~ 1 cup confectioners' sugar, sifted\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 2 ½ cups confectioners' sugar, sifted, divided\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Place strawberries in a blender; puree until smooth.\n" +
                "\n" +
                "Step 2\n" +
                "Transfer strawberry puree to a saucepan over medium heat; bring to a boil, stirring often, until puree is reduced by at least half, about 20 minutes. Remove from heat and cool completely.\n" +
                "\n" +
                "Step 3\n" +
                "Beat butter with an electric mixer in a bowl until light and fluffy.\n" +
                "\n" +
                "Step 4\n" +
                "Beat 1 cup confectioners' sugar into butter until just blended.\n" +
                "\n" +
                "Step 5\n" +
                "Beat 2 tablespoons strawberry puree and vanilla extract into butter mixture until just blended.\n" +
                "\n" +
                "Step 6\n" +
                "Repeat with 1 cup confectioners' sugar, followed by 2 tablespoons strawberry puree two more times.\n" +
                "\n" +
                "Step 7\n" +
                "Beat last 1/2 cup confectioners' sugar into mixture until just blended.", R.drawable.realstrawberryfrosting))
        foodlist.add(Food("Simple Strawberry Sauce", "This simple tasty strawberry sauce requires no cooking and is great for summer!\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Total: 15 mins\n" +
                "Servings: 12\n" +
                "Yield: 6 cups\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 quarts fresh strawberries, cleaned, hulled and sliced\n" +
                "~ ½ cup white sugar\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Place the strawberries in a large bowl. Sprinkle the sugar evenly over the berries, and stir to evenly coat all of the fruit. Let stand at room temperature for 10 minutes, stirring occasionally. Cover and chill until ready to serve.", R.drawable.simplestrawberrysauce))
        foodlist.add(Food("Chocolate Chunk Cookies", "Large or small, these triple chocolate cookies are crispy on the outside and chewy on the inside. Refrigerating the batter for 48 hours before baking is ideal, as this allows the dough to fully form its flavor.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 1 hr 25 mins\n" +
                "Servings: 12\n" +
                "Yield: 12 cookies\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ¾ cup brown sugar\n" +
                "~ ½ cup unsalted butter, at room temperature\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 teaspoon salt\n" +
                "~ 1 large egg\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 ½ cups all-purpose flour\n" +
                "~ ¾ teaspoon baking soda\n" +
                "~ 1 cup milk chocolate chips\n" +
                "~ 1 (3 ounce) bar dark chocolate, cut into chunks\n" +
                "~ 3 tablespoons white chocolate chips\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine brown sugar, butter, white sugar, and salt in a large bowl; beat with an electric mixer until a creamy, deep brown mixture forms. Add egg and vanilla; beat until mixture lightens and becomes smooth, 10 to 15 seconds.\n" +
                "\n" +
                "Step 2\n" +
                "Mix flour and baking soda together in a separate bowl. Add slowly to the wet ingredients until mostly incorporated, but some white traces of flour remain. Fold in milk chocolate, dark chocolate, and white chocolate using a spatula, not the mixer. Cover the dough and refrigerate for at least 30 minutes, or up to 48 hours.\n" +
                "\n" +
                "Step 3\n" +
                "Preheat the oven to 350 degrees F (175 degrees C) when ready to bake.\n" +
                "\n" +
                "Step 4\n" +
                "Divide cold dough into 2 1/4-ounce portions and place on a cookie sheet.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven until the edges start to look golden brown and crispy, 10 to 12 minutes. Do not overbake; the centers will not look fully done. Cool until cookies are set, about 30 minutes.", R.drawable.triplechocolatechunkcookies))
        foodlist.add(Food("Raspberry Oatmeal Cookie Bars", "One of my favorite cookie bar recipes.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 40 mins\n" +
                "Total: 55 mins\n" +
                "Servings: 9\n" +
                "Yield: 1 8x8-inch pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ½ cup packed light brown sugar\n" +
                "~ 1 cup all-purpose flour\n" +
                "~ ¼ teaspoon baking soda\n" +
                "~ ⅛ teaspoon salt\n" +
                "~ 1 cup rolled oats\n" +
                "~ ½ cup butter, softened\n" +
                "~ ¾ cup seedless raspberry jam\n" +
                "\n" +
                "DIRECTION:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease one 8 inch square pan, and line with greased foil.\n" +
                "\n" +
                "Step 2\n" +
                "Combine brown sugar, flour, baking soda, salt, and rolled oats. Rub in the butter using your hands or a pastry blender to form a crumbly mixture. Press 2 cups of the mixture into the bottom of the prepared pan. Spread the jam to within 1/4 inch of the edge. Sprinkle the remaining crumb mixture over the top, and lightly press it into the jam.\n" +
                "\n" +
                "Step 3\n" +
                "Bake for 35 to 40 minutes in preheated oven, or until lightly browned. Allow to cool before cutting into bars.", R.drawable.deliciousraspberryoatmealcookiebars))
        foodlist.add(Food("Oatmeal Peanut Butter Cookies III", "These are so close to the Girl Scout oatmeal peanut butter cookies that you won't know the difference!\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 10 mins\n" +
                "Total: 40 mins\n" +
                "Servings: 12\n" +
                "Yield: 2 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ¾ cup all-purpose flour\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ¼ teaspoon baking powder\n" +
                "~ ½ teaspoon salt\n" +
                "~ ½ cup butter, softened\n" +
                "~ ½ cup peanut butter\n" +
                "~ ½ cup white sugar\n" +
                "~ ½ cup packed light brown sugar\n" +
                "~ 1 egg\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 cup quick cooking oats\n" +
                "~ 3 tablespoons butter, softened\n" +
                "~ 1 cup confectioners' sugar\n" +
                "~ ½ cup smooth peanut butter\n" +
                "~ 2 ½ tablespoons heavy whipping cream\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a large bowl, cream together 1/2 cup butter or margarine, 1/2 cup peanut butter, white sugar, brown sugar, and vanilla. Add egg and beat well.\n" +
                "\n" +
                "Step 2\n" +
                "In another bowl, combine the flour, baking soda, baking powder, and salt. Add these dry ingredients to the creamed mixture. Stir. Add oatmeal and stir.\n" +
                "\n" +
                "Step 3\n" +
                "Drop by teaspoons onto greased baking sheet, and press each mound down with a fork to form 1/4 inch thick cookies. Bake at 350 degrees F (175 degrees C) for 10 minutes, or until cookies are a light brown.\n" +
                "\n" +
                "Step 4\n" +
                "To Make Filling: Cream 3 tablespoons butter or margarine with the confectioners' sugar, 1/2 cup smooth peanut butter, and the cream. Spread filling onto half of the cooled cookies, then top with the other half to form sandwiches.", R.drawable.oatmealpeanutbuttercookiesii))
        foodlist.add(Food("Salted Chocolate Cookies", "The addition of sea salt takes these big, soft, chocolaty, fudgy cookies to another level. Chocolate lovers will find these cookies irresistible.\n" +
                "\n" +
                "Prep: 10 mins\n" +
                "Cook: 20 mins\n" +
                "Additional: 20 mins\n" +
                "Total: 50 mins\n" +
                "Servings: 12\n" +
                "Yield: 12 cookies\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ nonstick cooking spray\n" +
                "~ 1 cup all-purpose flour\n" +
                "~ ¼ cup unsweetened cocoa powder\n" +
                "~ 1 teaspoon baking powder\n" +
                "~ ½ teaspoon sea salt\n" +
                "~ ½ cup butter, cut into chunks\n" +
                "~ ¾ cup semisweet chocolate chips\n" +
                "~ ⅔ cup brown sugar\n" +
                "~ ½ cup white sugar\n" +
                "~ 2 eggs\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 tablespoon sea salt, or as needed\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Line 2 large baking sheets with parchment paper and spray with cooking spray.\n" +
                "\n" +
                "Step 2\n" +
                "Combine flour, cocoa powder, baking powder and 1/2 teaspoon sea salt in a bowl.\n" +
                "\n" +
                "Step 3\n" +
                "Place butter in a saucepan over low heat and cook until melted, 3 to 5 minutes. Add chocolate chips and stir until completely melted. Remove from the heat.\n" +
                "\n" +
                "Step 4\n" +
                "Combine brown sugar, white sugar, eggs, and vanilla extract in a bowl and beat with an electric mixer until creamy. Pour a small amount of the chocolate mixture into the egg mixture and stir until well combined. Slowly add remaining chocolate mixture, stirring constantly so the eggs don't curdle. Fold in the flour mixture until just combined. Do not overmix.\n" +
                "\n" +
                "Step 5\n" +
                "Drop 6 large scoops of batter about 2 inches apart onto each of the prepared baking sheets.\n" +
                "\n" +
                "Step 6\n" +
                "Bake in the preheated oven until cookies have spread and flattened to a diameter of 4 1/2 inches or larger, and the tops are crinkled and puffed, 12 to 14 minutes. Immediately sprinkle with sea salt.\n" +
                "\n" +
                "Step 7\n" +
                "Place baking sheets on a wire rack and cool for 18 to 20 minutes. Remove cookies with a spatula onto serving platter.", R.drawable.saltedchocolatecookies))
        foodlist.add(Food("West Coast Trail Cookies", "This cookie gets its name from the West Coast Trail on Vancouver Island. It's my adaptation of the perfect nut-free trail cookie that's been our family's favorite chewy oatmeal cookie for 10 years. Loaded with choco-chips and dried cranberries. The flax seed meal and pumpkin seeds give them a nutty taste, without the nuts. Perfect for school kids or those with allergies.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 12 mins\n" +
                "Additional: 10 mins\n" +
                "Total: 42 mins\n" +
                "Servings: 36\n" +
                "Yield: 3 dozen cookies\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup unbleached all-purpose flour\n" +
                "~ ½ cup whole wheat flour\n" +
                "~ ⅓ cup flax seed meal\n" +
                "~ 1 teaspoon ground cinnamon\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ¼ teaspoon salt\n" +
                "~ 1 cup unsalted butter, softened\n" +
                "~ 1 cup dark brown sugar\n" +
                "~ ½ cup white sugar\n" +
                "~ 2 eggs\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 2 cups rolled oats\n" +
                "~ 1 cup semisweet chocolate chips\n" +
                "~ 1 cup dried cranberries\n" +
                "~ ¾ cup unsweetened shredded coconut\n" +
                "~ ½ cup coarsely chopped pumpkin seeds\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Line 2 baking sheets with parchment paper.\n" +
                "\n" +
                "Step 2\n" +
                "Whisk together all-purpose flour, whole wheat flour, flax seed meal, cinnamon, baking soda, and salt in a bowl.\n" +
                "\n" +
                "Step 3\n" +
                "Beat together butter, brown sugar, and white sugar in a large bowl until smooth and creamy; Stir in eggs and vanilla. Stir flour mixture into creamed butter mixture until dough is just combined. Fold oats, chocolate chips, cranberries, coconut, and pumpkin seeds into dough just until evenly combined.\n" +
                "\n" +
                "Step 4\n" +
                "Shape dough into 1 1/2-inch balls or drop by heaping tablespoonfuls onto the prepared baking sheets about 2 inches apart; press to flatten slightly.\n" +
                "\n" +
                "Step 5\n" +
                "Bake cookies in the preheated oven until lightly golden at the edges, about 12 minutes. Cool on the baking sheet for 10 minutes; transfer to a wire rack to cool completely.", R.drawable.westcoasttrailcookies))
        foodlist.add(Food("Spring Lime Tea Cookies", "These are light, buttery tea cookies bursting with citrus flavor. Perfect for a spring day.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 20 mins\n" +
                "Total: 50 mins\n" +
                "Servings: 24\n" +
                "Yield: 2 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 teaspoons lime juice\n" +
                "~ ⅓ cup milk\n" +
                "~ ½ cup butter, softened\n" +
                "~ ¾ cup white sugar\n" +
                "~ 1 egg\n" +
                "~ 2 teaspoons lime zest\n" +
                "~ 1 ¾ cups all-purpose flour\n" +
                "~ 1 teaspoon baking powder\n" +
                "~ ¼ teaspoon baking soda\n" +
                "~ 2 tablespoons lime juice\n" +
                "~ ¼ cup white sugar\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Combine the 2 teaspoons of lime juice with the milk, let stand for 5 minutes.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, cream together the butter and 3/4 cup sugar until light and fluffy. Beat in the egg, then stir in the lime zest and milk mixture. Combine the flour, baking powder and baking soda, blend into the creamed mixture. Drop by rounded spoonfuls onto the ungreased cookie sheets.\n" +
                "\n" +
                "Step 3\n" +
                "Bake for 8 to 10 minutes in the preheated oven, until the edges are light brown. Allow cookies to cool on baking sheets for 5 minutes before transferring to a wire rack to cool completely.\n" +
                "\n" +
                "Step 4\n" +
                "To make the glaze, stir together the remaining lime juice and sugar. Brush onto cooled cookies.", R.drawable.springlimeteacookies))
        foodlist.add(Food("Snickerdoodles V", "Is there anyone who doesn't like snickerdoodles? This recipe is a classic.\n" +
                "\n" +
                "Servings: 18\n" +
                "Yield: 3 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup shortening\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ 2 eggs\n" +
                "~ 2 ¾ cups sifted all-purpose flour\n" +
                "~ 1 teaspoon baking soda\n" +
                "~ 2 teaspoons cream of tartar\n" +
                "~ ½ teaspoon salt\n" +
                "~ 2 tablespoons white sugar\n" +
                "~ 2 teaspoons ground cinnamon\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 375 degrees F (190 degrees C). Grease cookie sheets.\n" +
                "\n" +
                "Step 2\n" +
                "In a medium bowl, cream together the shortening and sugar. Add eggs one at a time, mixing after each. Sift together the flour, baking soda, cream of tartar and salt; stir into the creamed mixture until well blended.\n" +
                "\n" +
                "Step 3\n" +
                "In a small shallow bowl, stir together the 2 tablespoons of sugar with the cinnamon. Roll the dough into walnut sized balls and roll the balls in the sugar mixture. Place cookies 2 inches apart on the prepared cookie sheet. Bake for 8 to 10 minutes in the preheated oven. Cookies should be slightly golden at the edges. Remove to cool on wire racks.", R.drawable.snickerdoodlesv))
        foodlist.add(Food("Lemon Rhubarb Bars", "Sweet crunchy crust with a tart filling makes these lemon rhubarb bars deliciously sweet, yet tart. Warning: they are also highly addictive!\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 20 mins\n" +
                "Total: 1 hr 40 mins\n" +
                "Servings: 24\n" +
                "Yield: 1 12x9-inch pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "\n" +
                "CRUST:\n" +
                "~ 2 cups flour\n" +
                "~ 1 cup unsalted butter\n" +
                "~ ½ cup white sugar\n" +
                "\n" +
                "FILLING:\n" +
                "~ 4 eggs\n" +
                "~ 1 cup white sugar, or to taste\n" +
                "~ 6 tablespoons all-purpose flour\n" +
                "~ 2 cups chopped rhubarb, or more to taste\n" +
                "~ 1 cup shredded coconut\n" +
                "~ 6 tablespoons lemon juice\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Mix 2 cups flour, butter, and 1/2 cup white sugar together in a bowl until crumbly; press into the bottom of a 12x9-inch baking dish.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven until crust is light brown, 25 to 30 minutes.\n" +
                "\n" +
                "Step 4\n" +
                "Beat eggs, 1 cup sugar, and 6 tablespoons flour together in a bowl until smooth; add rhubarb, coconut, lemon juice, and vanilla extract and stir to coat. Pour the rhubarb mixture over the crust.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in preheated oven until the fruit layer is set, 35 to 40 minutes. Let bars cool completely before serving.", R.drawable.lemonrhubarbbars))
        foodlist.add(Food("Blueberry Drop Cookies", "A delicious alternative to chocolate chip cookies.\n" +
                "\n" +
                "Servings: 48\n" +
                "Yield: 4 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 cups all-purpose flour\n" +
                "~ 2 teaspoons baking powder\n" +
                "~ ½ teaspoon salt\n" +
                "~ ½ cup shortening\n" +
                "~ ¼ cup milk\n" +
                "~ 1 egg\n" +
                "~ 1 cup white sugar\n" +
                "~ 1 teaspoon almond extract\n" +
                "~ 1 ½ teaspoons lemon zest\n" +
                "~ 1 cup fresh blueberries\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a large mixing bowl, cream the shortening, sugar, egg, milk, almond extract and lemon zest. Mix well after the addition of each ingredient. Combine the flour, baking powder and salt; blend into the sugar mixture. Fold in the blueberries. Cover and chill for 4 hours.\n" +
                "\n" +
                "Step 2\n" +
                "Preheat oven to 375 degrees F. Drop dough by teaspoonfuls onto ungreased cookie sheets, about 1 1/2 inches apart.\n" +
                "\n" +
                "Step 3\n" +
                "Bake 12 to 15 minutes in the preheated oven. Let the cookies cool on the baking sheets for a few minutes before transferring to wire racks to cool completely.", R.drawable.blueberrydropcookies))
        foodlist.add(Food("Carrot Cake Cheesecake", "A great carrot cake and a traditional cheesecake all in one bite. \n" + "\n" + "Prep: 30 mins\n" +
                "Cook: 1 hr 10 mins\n" +
                "Additional: 9 hrs\n" +
                "Total: 10 hrs 40 mins\n" +
                "Servings: 16\n" +
                "Yield: 1 9-inch springform pan\n" + "\n" + "INGREDIENTS:\n" +  "~ cooking spray"+"\n"+ "\n" + "CHEESECAKE:" + "\n" +
                "~ 3 (8 ounce) packages cream cheese, at room temperature\n" +
                "~ 1 cup white sugar\n" +
                "~ 4 eggs\n" +
                "~ 1 cup sour cream\n" +
                "~ ½ teaspoon vanilla extract\n" +
                "~ 3 tablespoons all-purpose flour \n" + "\n" + "CARROT CAKE:" + "\n" + "~ ¾ cup packed dark brown sugar\n" +
                "~ ⅔ cup canola oil\n" +
                "~ 1 egg\n" +
                "~ 1 cup all-purpose flour\n" +
                "~ ¾ teaspoon ground cinnamon\n" +
                "~ ½ teaspoon baking powder\n" +
                "~ ½ teaspoon salt\n" +
                "~ 1 cup grated carrots\n" +
                "~ ⅓ cup chopped walnuts\n" + "\n" + "DIRECTIONS:\n" + "\n" + "Step 1\n" +
                "Position the oven racks in the center and lower third of the oven. Preheat oven to 350 degrees F (175 degrees C). Fill a roasting pan halfway with water and place in the lower rack in the oven. Spray the inside of a 9-inch springform pan with cooking spray.\n"
                + "\n" + "Step 2\n" +
                "Beat three 8-ounce packages cream cheese in a bowl using an electric mixer on high speed until fluffy; reduce speed to medium-low. Add white sugar and beat 2 minutes. Beat 4 eggs, 1 at a time, into cream cheese mixture, beating well before adding the next egg. Beat sour cream and vanilla extract into cream cheese mixture until smooth; add 3 tablespoons flour and beat until cheesecake mixture is smooth.\n" +
                "\n" +
                "Step 3\n" +
                "Beat brown sugar and canola oil together in a separate bowl using an electric mixer on medium speed until smooth. Add 1 egg and beat for 2 minutes; reduce speed to low. Add 1 cup flour, cinnamon, baking powder, and salt and beat until batter is combined. Fold carrots and walnuts into batter.\n" +
                "\n" +
                "Step 4\n" +
                "Spread carrot cake batter in the prepared pan; top with cheesecake mixture. Place pan on the rack above the roasting pan with water in the oven.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven until cheesecake is set, about 1 hour 10 minutes, covering top of pan with aluminum foil if browning too quickly. Cool for 10 minutes.\n" +
                "\n" +
                "Step 6\n" +
                "Run a knife around the edge of pan to loosen cake; cool to room temperature in pan on a wire rack. Refrigerate at least 8 hours. Remove the outside of the springform pan.\n" +
                "\n" +
                "Step 7\n" +
                "Beat confectioners' sugar, 4 ounces cream cheese, and almond extract together in a bowl using an electric mixer until frosting is smooth. Spread frosting on top of cheesecake layer.", R.drawable.carrotcakecheesecake))
        foodlist.add(Food("Rhubarb Cheesecake", "An exquisite cheesecake made with fresh rhubarb and finished with sour cream topping.\n" + "\n" + "Prep: 20 mins\n" +
                "Cook: 45 mins\n" +
                "Total: 1 hr 5 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 9-inch cheesecake\n" + "\n" + "INGREDIENTS:\n" + "~ 1 cup all-purpose flour\n" +
                "~ ¼ cup white sugar\n" +
                "~ ½ cup butter\n" +
                "~ 3 cups chopped rhubarb\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 tablespoon all-purpose flour\n" +
                "~ 2 (8 ounce) packages cream cheese\n" +
                "~ ½ cup white sugar\n" +
                "~ 2 eggs\n" +
                "~ 1 cup sour cream\n" +
                "~ 2 tablespoons white sugar\n" +
                "1 teaspoon vanilla extract\n" + "\n" + "DIRECTIONS:\n" + "\n" + "Step 1\n" +
                "Preheat oven to 375 degrees F (190 degrees C). In a medium bowl, combine 1 cup flour, 1/4 cup sugar and 1/2 cup butter. Mix until crumbly and pat into the bottom of a 9 inch springform pan.\n" + "\n" +
                "Step 2\n" +
                "In a medium bowl, toss together the chopped rhubarb, 1/2 cup sugar and 1 tablespoon flour. Pour onto crust. Bake in preheated oven for 15 minutes. Remove from oven and set aside. Reduce oven temperature to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 3\n" +
                "In a large bowl, beat the cream cheese and 1/2 cup sugar until creamy. Beat in the eggs one at a time. Pour over hot rhubarb in the pan.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven for 30 minutes, or until filling is set. Cover with sour cream topping while still hot.\n" +
                "\n" +
                "Step 5\n" +
                "To make the sour cream topping: In a small bowl, combine 1 cup sour cream, 2 tablespoons sugar and 1 teaspoon vanilla. Mix well and spread on top of cake.", R.drawable.rhubarbcheesecake))
        foodlist.add(Food("Lemon Meringue Cheesecake", "Love lemon meringue pie and cheesecake? Well this is the best of both worlds. Great any time of year and sure to impress your guests.\n" +
                "\n" + "Prep: 20 mins\n" +
                "Cook: 1 hr 10 mins\n" +
                "Additional: 4 hrs\n" +
                "Total: 5 hrs 30 mins\n" +
                "Servings: 16\n" +
                "Yield: 16 servings\n" + "\n" + "INGREDIENTS:" + "\n" + "~ 2 cups shortbread cookie crumbs\n" +
                "~ ¼ cup melted butter\n" +
                "~ 3 (8 ounce) packages cream cheese, softened\n" +
                "~ 1 cup sour cream\n" +
                "~ 1 cup white sugar\n" +
                "~ 4 eggs\n" +
                "~ ¼ cup fresh lemon juice\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 lemon, zested\n" +
                "~ 4 egg whites\n" +
                "~ ¼ cup white sugar\n" +
                "¼ teaspoon cream of tartar (Optional)\n" +
                "1 ½ cups lemon curd\n" + "\n" + "DIRECTIONS:\n" + "\n" + "Step 1\n" +
                "Preheat oven to 325 degrees F (165 degrees C).\n" + "\n" + "Step 2\n" +
                "Mix cookie crumbs and melted butter together in a bowl until evenly combined; press into the bottom of a 9-inch springform pan.\n" +
                "\n" +
                "Step 3\n" +
                "Beat cream cheese, sour cream, and 1 cup sugar together in a bowl using an electric mixer until smooth and creamy. Add eggs one at a time, beating well after each addition. Mix lemon juice, vanilla extract, and lemon zest into cream cheese mixture, scraping bottom and sides of the bowl. Spread mixture over cookie crust in the springform pan.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven until almost set in the center, about 1 hour. Cool to room temperature and refrigerate until completely cooled, at least 3 to 4 hours.\n" +
                "\n" +
                "Step 5\n" +
                "Preheat oven to 375 degrees F (190 degrees C).\n" +
                "\n" +
                "Step 6\n" +
                "Beat egg whites in a bowl using an electric mixer until soft peaks form; add 1/4 cup sugar and cream of tartar and beat until stiff, but not dry, peaks form.\n" +
                "\n" +
                "Step 7\n" +
                "Spread lemon curd over cheesecake. Mound whipped egg whites over the curd, sealing around all the edges.\n" +
                "\n" +
                "Step 8\n" +
                "Bake in the preheated oven until meringue is golden brown, about 10 minutes. Chill uncovered in refrigerator, about 1 hour.", R.drawable.lemonmeringuecheesecake))
        foodlist.add(Food("Apricot Cheesecake", "I entertain frequently. My guests always expect me to create something new, a real showstopper! Here is my latest creation. Decorate with whipped cream just before serving.\n" + "\n" + "Prep: 40 mins\n" +
                "Cook: 1 hr 8 mins\n" +
                "Additional: 7 hrs\n" +
                "Total:8 hrs 48 mins\n" +
                "Servings: 12\n" +
                "Yield: 12 servings\n" + "\n" + "INGREDIENTS:\n" + "\n" + "CRUST:\n" +
                "~ 1 ⅓ cups almond flour\n" +
                "~ 3 tablespoons brown sugar\n" +
                "~ 2 tablespoons sliced almonds, chopped\n" +
                "~ 2 tablespoons butter, melted\n" + "\n" + "CHEESECAKE:\n" +
                "~ 8 ounces dried apricots\n" +
                "~ water to cover\n" +
                "~ 2 tablespoons amaretto liqueur\n" +
                "~ 1 teaspoon white sugar, or to taste\n" +
                "~ 3 (8 ounce) packages cream cheese, softened\n" +
                "~ ¾ cup white sugar\n" +
                "~ ½ cup sour cream\n" +
                "~ 2 tablespoons cornstarch\n" +
                "~ 1 ½ teaspoons vanilla extract\n" +
                "~ 3 eggs, at room temperature\n" + "\n" + "TOPPING:\n" +
                "~ 1 ½ cups sour cream\n" +
                "~ 3 tablespoons white sugar\n" +
                "~ 1 teaspoon vanilla extract\n" + "\n" + "DIRECTIONS\n" + "\n" + "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Line a 9-inch springform pan with parchment paper.\n" + "\n" +
                "Step 2\n" +
                "Combine almond flour, brown sugar, and almonds in a bowl. Add melted butter; stir with a fork until the mixture resembles wet sand. Spread on the bottom of the parchment-lined pan.\n" +
                "\n" +
                "Step 3\n" +
                "Combine apricots and water in a saucepan; bring to a boil and simmer until very soft, about 10 minutes. Drain; mash apricots into a pulp. Add amaretto; mix well. Sweeten with 1 teaspoon white sugar.\n" +
                "\n" +
                "Step 4\n" +
                "Beat cream cheese, 3/4 cup white sugar, 1/2 cup sour cream, cornstarch, and 1 1/2 teaspoons vanilla extract in a bowl using an electric mixer on low speed. Add eggs one at a time; mix well. Fold in 2 tablespoons of apricot pulp; reserve remainder for topping. Pour cheesecake batter into the pan, covering the crust.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven until edges are puffed and surface of cheesecake is firm except for a small spot in the center that will jiggle when the pan is gently shaken, about 45 minutes.\n" +
                "\n" +
                "Step 6\n" +
                "Beat 1 1/2 cups sour cream, 3 tablespoons white sugar, and 1 teaspoon vanilla extract together until combined. Remove cheesecake from the hot oven; spoon topping over the surface and return cake to the oven for 8 minutes more.\n" +
                "\n" +
                "Step 7\n" +
                "Cool to room temperature, about 1 hour. Refrigerate for 6 to 8 hours. Top with remaining apricot pulp.", R.drawable.aapricotcheesecake))
        foodlist.add(Food("Key Lime Cheesecake I", "My favorite dessert to make has to be cheesecakes. This is just one of many that gets a lot of requests. It is sweet and tangy just like its more familiar cousin, the pie. I usually serve it with whipped cream and lime slices. Enjoy!\n" + "\n" + "Servings: 12\n" +
                "Yield: 1 - 9 inch cheesecake\n" + "\n" + "INGREDIENTS:\n" + "~ 1 ½ cups graham cracker crumbs\n" +
                "~ 6 tablespoons butter, melted\n" +
                "~ 24 ounces cream cheese, softened\n" +
                "~ 1 cup white sugar\n" +
                "~ 1 tablespoon cornstarch\n" +
                "~ 3 eggs\n" +
                "~ 1 tablespoon grated lime zest\n" +
                "~ ⅔ cup key lime juice\n" + "\n" + "DIRECTIONS:\n" + "\n" + "Step 1\n" +
                "Combine cookie or graham cracker crumbs with butter or margarine. Press into bottom and partially up sides of 9 inch springform pan. Refrigerate.\n" + "\n" + "Step 2\n" +
                "In a large bowl, beat with an electric mixer the cream cheese, sugar, lime peel, and cornstarch until smooth and fluffy. Beat in eggs one at a time, blending just until smooth. Add key lime juice with mixer on low. Finish mixing by hand. Do not over beat, or cake will crack during baking. Pour batter into prepared crust.\n" +
                "\n" +
                "Step 3\n" +
                "Bake at 300 degrees F (150 degrees C) for 55 to 65 minutes, or until set. To minimize cracking, place a shallow pan half full of hot water on lower rack during baking.\n" +
                "\n" +
                "Step 4\n" +
                "Turn oven off, and let cheesecake stand in oven 30 minutes with the door open at least 4 inches. Remove from oven. Refrigerate cake overnight, and up to three days.", R.drawable.keylimecheesecakei ))
        foodlist.add(Food("Strawberry Cheesecake", "This creamy strawberry cheesecake is perfect for any special occasion! You have to bake the cake and let it cool for an hour, then chill it in the refrigerator for at least 4 hours prior to serving.\n" +
                "\n" +
                "Prep: 1 hr\n" +
                "Cook: 45 mins\n" +
                "Additional: 9 hrs\n" +
                "Total: 10 hrs 45 mins\n" +
                "Servings: 12\n" +
                "Yield: 12 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 ¼ cups graham cracker crumbs\n" +
                "~ ¼ cup white sugar\n" +
                "~ 2 teaspoons ground cinnamon\n" +
                "~ ⅓ cup butter, melted\n" +
                "~ 2 (10 ounce) packages frozen sweetened sliced strawberries, thawed and drained\n" +
                "~ 1 tablespoon cornstarch\n" +
                "~ 3 (8 ounce) packages cream cheese, softened\n" +
                "~ 1 (14 ounce) can sweetened condensed milk\n" +
                "~ ¼ cup lemon juice\n" +
                "~ ½ teaspoon vanilla extract\n" +
                "~ 3 eggs\n" +
                "~ 1 tablespoon water (Optional)\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine graham cracker crumbs, sugar, cinnamon, and butter in a bowl. Press onto the bottom of an ungreased 9-inch springform pan. Refrigerate for 30 minutes.\n" +
                "\n" +
                "Step 2\n" +
                "Preheat oven to 300 degrees F (150 degrees C).\n" +
                "\n" +
                "Step 3\n" +
                "Place strawberries and cornstarch into a blender. Cover and puree until smooth. Pour strawberry sauce into a saucepan.\n" +
                "\n" +
                "Step 4\n" +
                "Bring to a boil over high heat. Boil and stir until sauce is thick and shiny, about 2 minutes. Set aside 1/3 cup strawberry sauce; cool. Cover and refrigerate remaining sauce for serving.\n" +
                "\n" +
                "Step 5\n" +
                "Beat cream cheese in a mixing bowl with an electric mixer until light and fluffy; gradually beat in condensed milk. Mix in lemon juice and vanilla extract, then beat in eggs on low speed until just combined. Pour half of cream cheese mixture over crust; drop half of reserved strawberry sauce by 1/2 teaspoonfuls on cream cheese layer. Carefully spoon remaining cream cheese mixture over sauce; drop remaining strawberry sauce by 1/2 teaspoonfuls on top. Cut through top layer only with a knife to swirl strawberry sauce.\n" +
                "\n" +
                "Step 6\n" +
                "Bake in preheated oven until center is almost set, 45 to 50 minutes. Cool on a wire rack for 10 minutes. Carefully run a knife around edge of pan to loosen; cool 1 hour longer. Refrigerate overnight. Serve reserved strawberry sauce with cheesecake. If the sauce it too thick, stir in water.", R.drawable.strawberrycheesecake))
        foodlist.add(Food("Raspberry Cheesecake", "This makes an excellent cheesecake, similar to one you would get in a restaurant. Great for special occasions! Garnish with white chocolate curls if desired.\n" +
                "\n" +
                "Prep: 1 hr\n" +
                "Cook: 1 hr\n" +
                "Additional: 8 hrs\n" +
                "Total: 10 hrs\n" +
                "Servings: 16\n" +
                "Yield: 1 - 9 inch cheesecake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup chocolate cookie crumbs\n" +
                "~ 3 tablespoons white sugar\n" +
                "~ ¼ cup butter, melted\n" +
                "~ 1 (10 ounce) package frozen raspberries\n" +
                "~ 2 tablespoons white sugar\n" +
                "~ 2 teaspoons cornstarch\n" +
                "~ ½ cup water\n" +
                "~ 2 cups white chocolate chips\n" +
                "~ ½ cup half-and-half cream\n" +
                "~ 3 (8 ounce) packages cream cheese, softened\n" +
                "~ ½ cup white sugar\n" +
                "~ 3 eggs\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "\n" +
                "DIRECTIONS\n" +
                "\n" +
                "Step 1\n" +
                "In a medium bowl, mix together cookie crumbs, 3 tablespoons sugar, and melted butter. Press mixture into the bottom of a 9 inch springform pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a saucepan, combine raspberries, 2 tablespoons sugar, cornstarch, and water. Bring to boil, and continue boiling 5 minutes, or until sauce is thick. Strain sauce through a mesh strainer to remove seeds.\n" +
                "\n" +
                "Step 3\n" +
                "Preheat oven to 325 degrees F (165 degrees C). In a metal bowl over a pan of simmering water, melt white chocolate chips with half-and-half, stirring occasionally until smooth.\n" +
                "\n" +
                "Step 4\n" +
                "In a large bowl, mix together cream cheese and 1/2 cup sugar until smooth. Beat in eggs one at a time. Blend in vanilla and melted white chocolate. Pour half of batter over crust. Spoon 3 tablespoons raspberry sauce over batter. Pour remaining cheesecake batter into pan, and again spoon 3 tablespoons raspberry sauce over the top. Swirl batter with the tip of a knife to create a marbled effect.\n" +
                "\n" +
                "Step 5\n" +
                "Bake for 55 to 60 minutes, or until filling is set. Cool, cover with plastic wrap, and refrigerate for 8 hours before removing from pan. Serve with remaining raspberry sauce.", R.drawable.whitechocolateraspberrycheesecake))
        foodlist.add(Food("Pineapple Cheesecake", "A delicious blend of pineapple and cream cheese in a graham cracker crust. When ready to serve, pull up a chair and enjoy!\n" +
                "\n" +
                "Prep: 10 mins\n" +
                "Additional: 2 hrs\n" +
                "Total: 2 hrs 10 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 - 9 inch pie\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (8 ounce) package cream cheese, softened\n" +
                "~ ½ cup white sugar\n" +
                "~ 2 (15 ounce) cans crushed pineapple, drained\n" +
                "~ 1 ¾ cups frozen whipped topping, thawed\n" +
                "~ 1 (9 inch) prepared graham cracker crust\n" +
                "\n" +
                "DIRECTIONS\n" +
                "\n" +
                "Step 1\n" +
                "In a large bowl, mix cream cheese and sugar together. Stir in 1 can of pineapple and whipped topping to cream cheese mixture. Mix until smooth.\n" +
                "\n" +
                "Step 2\n" +
                "Pour mixture into crust and top with other can of pineapple. Cover and chill for 2 hours.", R.drawable.pineapplecheesecake))
        foodlist.add(Food("Banana Cheesecake", "This simple recipe is a combination of banana cheesecake and banana cream pie all rolled into one dessert! It uses an Oreo®-cookie crust instead of the traditional graham cracker crust, adding a bit of a chocolate accent. Plus, it uses fat-free cream cheese, but proves to taste rich and creamy for a banana-y treat! Garnish with whipped cream and sliced bananas, if desired.\n" +
                "\n" +
                "Prep: 40 mins\n" +
                "Cook: 50 mins\n" +
                "Additional: 4 hrs\n" +
                "Total: 5 hrs 30 mins\n" +
                "Servings: 10\n" +
                "Yield: 10 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "\n" +
                "CHEESECAKE:\n" +
                "~ 2 cups crushed chocolate sandwich cookies (such as Oreo®)\n" +
                "~ 1 tablespoon butter, melted\n" +
                "~ 3 (8 ounce) packages fat-free cream cheese, softened\n" +
                "~ ¾ cup white sugar\n" +
                "~ 3 eggs\n" +
                "~ ½ cup mashed banana\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "\n" +
                "TOPPING:\n" +
                "~ 2 ½ cups cold milk\n" +
                "~ 2 (3.4 ounce) packages instant vanilla pudding mix\n" +
                "~ 1 cup fat-free frozen whipped topping, thawed\n" +
                "~ 1 sliced banana, or more to taste\n" +
                "\n" +
                "DIRECTIONS\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Mix cookie crumbs and butter together in a bowl. Press mixture into the bottom of a 9-inch springform pan.\n" +
                "\n" +
                "Step 3\n" +
                "Mix cream cheese and sugar together in a bowl using an electric mixer until smooth and well blended. Add eggs 1 at a time, mixing after each addition. Stir in mashed banana and vanilla extract. Pour mixture into crust in the springform pan.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven until center is almost set, 50 to 60 minutes. Cool completely, at least 1 hour, before removing from pan.\n" +
                "\n" +
                "Step 5\n" +
                "Whisk milk and pudding mix together in a bowl until thoroughly combined. Fold in whipped topping.\n" +
                "\n" +
                "Step 6\n" +
                "Arrange sliced bananas on top of cooled cheesecake. Spread pudding mixture on top. Chill at least 3 hours before serving.", R.drawable.bananacheesecakewithbananacreampietopping))
        foodlist.add(Food("Lemon Cheesecake", "This is very good. You can't even tell it is made with low-fat ingredients.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 - 9 inch pie\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (9 inch) reduced fat graham cracker pie crust\n" +
                "~ 2 (8 ounce) packages cream cheese\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 ½ tablespoons all-purpose flour\n" +
                "~ 2 ½ tablespoons lemon juice\n" +
                "~ ½ cup egg substitute\n" +
                "~ 1 (8 ounce) container nonfat lemon yogurt\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C.)\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, beat cream cheese and sugar until smooth. Beat in flour. Beat in lemon juice and egg substitute. Stir in yogurt. Pour into crust.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven for 50 to 60 minutes, or until filling is set. Allow to cool. Refrigerate at least 4 hours before serving.", R.drawable.lemoncheesecake))
        foodlist.add(Food("Citrus Cheesecake", "A great tasting citrus cheesecake with a marmalade glaze. Garnish with orange slices and sprigs of mint.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr 10 mins\n" +
                "Additional: 20 mins\n" +
                "Total: 2 hrs\n" +
                "Servings: 12\n" +
                "Yield: 1 - 9 inch springform pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 egg yolk\n" +
                "~ 1 tablespoon fresh lemon juice\n" +
                "~ 1 teaspoon grated lemon zest\n" +
                "~ ¼ teaspoon vanilla extract\n" +
                "~ 1 ¼ cups all-purpose flour\n" +
                "~ ⅓ cup white sugar\n" +
                "~ ½ cup butter, room temperature\n" +
                "~ 1 egg white\n" +
                "~ 3 (8 ounce) packages cream cheese\n" +
                "~ 1 ⅔ cups white sugar\n" +
                "~ 2 tablespoons cornstarch\n" +
                "~ 1 tablespoon fresh lemon juice\n" +
                "~ 1 tablespoon grated orange zest\n" +
                "~ 2 teaspoons grated lime zest\n" +
                "~ 1 ½ teaspoons grated lemon zest\n" +
                "~ ½ teaspoon vanilla extract\n" +
                "~ 3 eggs\n" +
                "~ 1 cup sour cream\n" +
                "~ ⅔ cup orange marmalade\n" +
                "~ 2 teaspoons fresh lemon juice\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "Step 1\n" +
                "Preheat oven to 450 degrees F (230 degrees C). Butter a 9 inch springform pan. In a small bowl, whisk together egg yolk, 1 tablespoon lemon juice, 1 teaspoon lemon peel and 1/4 teaspoon vanilla. In the bowl of a food processor, combine flour and 1/3 cup sugar. Add butter and process until coarse crumbs form. With machine running, add yolk mixture and blend until moist clumps form. Press dough onto bottom and 1 1/2 inches up sides of prepared pan. Freeze crust 10 minutes.\n" +
                "\n" +
                "Step 2\n" +
                "Brush crust lightly with egg white. Bake until crust is pale golden, about 15 minutes. Cool on rack while preparing filling. Reduce oven temperature to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 3\n" +
                "In a large bowl, beat cream cheese and 1 2/3 cups sugar until smooth. Beat in cornstarch, 1 tablespoon lemon juice, orange zest, lime zest, 1 1/2 teaspoon lemon zest and 1/2 teaspoon vanilla. Beat in eggs one at a time, then stir in sour cream. Pour filling into crust.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven for 55 to 60 minutes, or until puffed and cracked around edges and center moves only slightly when pan is gently shaken. Allow to cool to room temperature, then refrigerate overnight.\n" +
                "\n" +
                "Step 5\n" +
                "In a saucepan over medium heat, boil marmalade and 2 teaspoons lemon juice until slightly reduced, about 2 minutes. Spread warm glaze on top of cake. Chill cake 10 minutes. Remove pan sides and transfer cake to serving plate.", R.drawable.citruscheesecake))
        foodlist.add(Food("Rhubarb Cheesecake Pie", "This recipe combines the best of a pie and a cheesecake. Nice and tangy. Garnish with whipped topping and almonds if desired.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 45 mins\n" +
                "Total: 1 hr 15 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 9-inch deep dish pie\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ¼ cup cornstarch\n" +
                "~ 1 cup white sugar\n" +
                "~ 1 pinch salt\n" +
                "~ ½ cup water\n" +
                "~ 3 cups chopped rhubarb\n" +
                "~ 1 (9 inch) prebaked deep dish pie shell\n" +
                "~ 1 (8 ounce) package cream cheese\n" +
                "~ 2 eggs\n" +
                "~ ½ cup white sugar\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 425 degrees F (220 degrees C.)\n" +
                "\n" +
                "Step 2\n" +
                "In a saucepan, combine cornstarch, 1 cup sugar, salt, water and rhubarb. Cook, stirring occasionally, until thickened. Pour into pie shell.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven for 10 minutes. Remove from oven and reduce temperature to 325 degrees F (165 degrees C.)\n" +
                "\n" +
                "Step 4\n" +
                "In a medium bowl, beat cream cheese, eggs and 1/2 cup sugar until smooth. Pour over rhubarb in the pan.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven for 35 minutes, or until filling is set.", R.drawable.rhubarbcheesecakepie))
        foodlist.add(Food("Hawaiian Cheesecake", "Hawaiian cheesecake. A recipe I created. It's delish! It was an instant favorite after the first time trying to make it. Super easy!\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 35 mins\n" +
                "Additional: 8 hrs\n" +
                "Total: 8 hrs 50 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 cheesecake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 (8 ounce) packages cream cheese, softened\n" +
                "~ 1 (14 ounce) can sweetened condensed milk\n" +
                "~ 1 cup granular no-calorie sucralose sweetener (such as Splenda®)\n" +
                "~ 1 cup finely flaked coconut\n" +
                "~ ¼ cup milk\n" +
                "~ ¼ cup crushed pecans\n" +
                "~ 2 eggs\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 1 pinch salt\n" +
                "~ 1 (9 inch) prepared graham cracker crust\n" +
                "~ 1 (8 ounce) container frozen whipped topping (such as Cool Whip®), thawed\n" +
                "~ 1 (15 ounce) can crushed pineapple, drained\n" +
                "~ 3 tablespoons finely flaked coconut, or to taste\n" +
                "~ 1 tablespoon crushed pecans, or to taste\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Beat cream cheese, sweetened condensed milk, sweetener, 1 cup coconut, milk, 1/4 cup pecans, eggs, vanilla extract, and salt together in a bowl until smooth; pour into prepared crust.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven until filling is set and browned slightly around the edges, 35 to 40 minutes.\n" +
                "\n" +
                "Step 4\n" +
                "Refrigerate cheesecake 8 hours to overnight.\n" +
                "\n" +
                "Step 5\n" +
                "Spread crushed pineapple evenly over cheesecake and top with whipped topping. Sprinkle 3 tablespoons coconut and 1 tablespoon pecans over the whipped topping.", R.drawable.hawaiiancheesecake))
        foodlist.add(Food("Italian Cream Cheese", "This is grandmother's cheesecake recipe passed down to their entire family. It's the best. I can't believe I'm sharing it, but everyone needs to know how to make an authentic Italian cheesecake. It is creamy and not thick, which is why our family LOVES it! For best results, do NOT substitute any ingredients with low-fat unless you've made it before and want to experiment.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 2 hrs\n" +
                "Additional: 4 hrs\n" +
                "Total: 6 hrs 15 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 9-inch cheesecake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 (8 ounce) packages cream cheese, softened\n" +
                "~ 1 (16 ounce) container ricotta cheese\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ 4 eggs\n" +
                "~ 1 tablespoon lemon juice\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 3 tablespoons cornstarch\n" +
                "~ 3 tablespoons flour\n" +
                "~ ½ cup butter, melted and cooled\n" +
                "~ 1 pint sour cream\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Lightly grease a 9-inch springform pan.\n" +
                "\n" +
                "Step 2\n" +
                "Mix the cream cheese and ricotta cheese together in a mixing bowl until well combined. Stir in the sugar, eggs, lemon juice, vanilla, cornstarch, flour, and butter. Add the sour cream last and stir. Pour the mixture into the prepared springform pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven 1 hour; turn oven off and leave in oven 1 hour more. Allow to cool completely in refrigerator before serving.", R.drawable.italiancreamcheeseandricottacheesecake))
        foodlist.add(Food("Green Tea Mousse Cheesecake", "Been looking for a no-bake Japanese-style green tea cheesecake for a long time? Well, this is the recipe created just for you.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 1 min\n" +
                "Additional: 7 hrs\n" +
                "Total: 7 hrs 21 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 9-inch cheesecake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (4.8 ounce) package graham crackers, crushed\n" +
                "~ 2 tablespoons white sugar\n" +
                "~ 3 tablespoons unsalted butter, melted\n" +
                "~ 2 tablespoons green tea powder (matcha)\n" +
                "~ ½ cup warm water\n" +
                "~ 2 tablespoons unflavored gelatin\n" +
                "~ ½ cup cold water\n" +
                "~ 2 cups whipping cream\n" +
                "~ 2 (8 ounce) packages cream cheese, at room temperature\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ ¼ cup honey\n" +
                "~ 2 eggs\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine the graham cracker crumbs with 2 tablespoons of sugar in a mixing bowl. Drizzle in the melted butter and mix until evenly moistened. Press into the bottom of a 9-inch springform pan lined with waxed paper; set aside.\n" +
                "\n" +
                "Step 2\n" +
                "Stir the tea powder into the warm water; set aside. Sprinkle the gelatin over the cold water; set aside.\n" +
                "\n" +
                "Step 3\n" +
                "Whip the cream to stiff peaks; set aside. Beat the cream cheese, 1/2 cup sugar, vanilla, and honey in a clean mixing bowl. Beat in the eggs one at a time until evenly blended. Cook the gelatin mixture in the microwave until melted, about 45 seconds. Beat the gelatin and tea into the cream cheese mixture, then fold in the whipped cream until smooth. Pour into the springform pan. Refrigerate 7 hours to overnight before unmolding and serving.", R.drawable.greenteamoussecheesecake))
        foodlist.add(Food("Grandmother's Cheesecake", "A citrus cheesecake with a shortbread crust. It takes a long time of preparation but it is well worth the wait. If you can't find a vanilla bean, substitute 1/4 teaspoon vanilla extract for each 1 inch piece of bean. You can top with any of your favorite fruits or toppings, or eat it plain. Summertime fresh fruits are nice, or chocolate lovers can spread on a layer of fudge topping.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 16\n" +
                "Yield: 1 - 9 inch springform pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup all-purpose flour\n" +
                "~ ¼ cup white sugar\n" +
                "~ 1 teaspoon grated lemon zest\n" +
                "~ 1 teaspoon grated orange zest\n" +
                "~ ½ vanilla bean\n" +
                "~ ½ cup unsalted butter\n" +
                "~ 1 egg yolk\n" +
                "~ 5 (8 ounce) packages cream cheese, softened\n" +
                "~ 1 ¾ cups white sugar\n" +
                "~ 3 tablespoons all-purpose flour\n" +
                "~ 1 ½ teaspoons grated lemon zest\n" +
                "~ 1 ½ teaspoons grated orange zest\n" +
                "~ ½ vanilla bean\n" +
                "~ 5 eggs\n" +
                "~ 2 egg yolks\n" +
                "~ ¼ cup heavy cream\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a large bowl, combine 1 cup flour, 1/4 cup sugar, 1 teaspoon lemon peel, and 1 teaspoon orange peel. Split a 1-inch piece of vanilla bean; scrape seeds into flour mixture. Cut in butter until mixture resembles course crumbs. Add 1 egg yolk; stir until all flour is moistened. Gather dough into ball, wrap in wax paper, and refrigerate for 1 hour and 30 minutes.\n" +
                "\n" +
                "Step 2\n" +
                "Lightly butter a 9 inch springform pan. Roll dough on lightly floured surface to 1/8 inch thick; trim to a 10 inch circle. Reserve pastry trimmings. Press pastry circle into bottom and 1/2 inch up sides of a 9 inch buttered springform pan. Refrigerate 1 hour.\n" +
                "\n" +
                "Step 3\n" +
                "Preheat oven to 400 degrees F (200 degrees C). Bake crust for 20 minutes, until lightly browned. Cool on wire rack.\n" +
                "\n" +
                "Step 4\n" +
                "Beat cream cheese, 1 3/4 cups sugar, 3 tablespoons flour, 1 1/2 teaspoons lemon peel and 1 1/2 teaspoons orange peel in large mixer on medium speed until light and fluffy, about 10 minutes. Split a 1 inch piece of vanilla bean and scrape seeds into cream cheese mixture. Beat in 5 eggs and 2 egg yolks, 1 at a time, on medium speed. Stir in cream.\n" +
                "\n" +
                "Step 5\n" +
                "Increase oven temperature to 500 degrees F (260 degrees C). Roll out reserved pastry trimmings on lightly floured board; cut into strips 2 inches wide. Pat strips up side of pan, pressing dough lightly to baked crust. Pour filling into crust. Bake 15 minutes.\n" +
                "\n" +
                "Step 6\n" +
                "Reduce oven temperature to 200 degrees F (95 degrees C). DO NOT OPEN OVEN DOOR! Bake until cheesecake is firm in center, about 50 minutes. Turn off oven; leave cheesecake in oven with door slightly open for 1 hour. Remove from oven, and cool completely on wire rack. Refrigerate at least 2 hours.", R.drawable.grandmothercheesecake))
        foodlist.add(Food("Lemon Pound Cake", "A lemony pound cake with the added addition of lemon pudding for an extra moist taste sensation\n" +
                "\n" +
                "Servings: 12\n" +
                "Yield: 1 -10 inch bundt pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (18.25 ounce) package lemon cake mix\n" +
                "~ 1 (3 ounce) package instant lemon pudding mix\n" +
                "~ 4 eggs\n" +
                "~ 1 cup water\n" +
                "~ ⅓ cup vegetable oil\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour one 10 inch bundt pan.\n" +
                "\n" +
                "Step 2\n" +
                "Combine cake mix, pudding mix, eggs, water, and oil in a large bowl. Beat at medium speed for 2 minutes. Pour into prepared pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake at 350 degrees F (175 degrees C) for 50 to 60 minutes. Let cake cool in pan for 10 minutes then remove from pan and let cool completely. Sprinkle with confectioner's sugar or frost with lemon frosting, if desired.", R.drawable.lemonpoundcake))
        foodlist.add(Food("Blueberry-Lemon Pound Cake", "This is a tangy variation of an old recipe. Blueberry pound cake with the added spark of lemon. I love it and so do my kids.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 16\n" +
                "Yield: 1 - 10 inch Bundt cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 cups butter, softened\n" +
                "~ 3 cups white sugar\n" +
                "~ 1 cup milk, room temperature\n" +
                "~ 6 eggs\n" +
                "~ 2 teaspoons lemon extract\n" +
                "~ 1 tablespoon baking powder\n" +
                "~ 4 cups unbleached all-purpose flour\n" +
                "~ 1 teaspoon grated lemon zest\n" +
                "~ 2 cups fresh blueberries\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Grease and flour a 10 inch Bundt pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, cream together the butter and sugar until light and fluffy. Beat in the eggs one at a time, then stir in the lemon extract. Combine the flour, baking powder, and lemon zest; stir into the batter alternating with the milk. I like to use a spatula and stir by hand, mixing just until blended so the batter is not over mixed. Be sure to scrape the bottom and sides of the bowl often. Fold in the blueberries. Spoon the batter into the prepared pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake for 1 hour in the preheated oven, or until a toothpick inserted into the center comes out clean. Let cool in the pan for at least 10 minutes, then invert onto a wire rack to cool completely.", R.drawable.blueberrylemonpoundcake))
        foodlist.add(Food("Old-Fashioned Lemon Pound Cake", "This is an old recipe that my grandmother always made. It goes great with coffee and is quick and easy to make. Light lemon flavor that can be accompanied with a lemon glaze if desired.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 30 mins\n" +
                "Total: 45 mins\n" +
                "Servings: 12\n" +
                "Yield: 2 loaves\n" +
                "\n" +
                "INGREDIETS:\n" +
                "~ 2 cups white sugar\n" +
                "~ 2 cups all-purpose flour\n" +
                "~ 5 eggs\n" +
                "~ 1 cup shortening (such as Crisco®)\n" +
                "~ 5 tablespoons whole milk\n" +
                "~ 1 tablespoon vanilla extract\n" +
                "~ 2 teaspoons lemon extract\n" +
                "~ ¼ teaspoon baking powder\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 325 degrees F (165 degrees C). Grease two 9x5-inch loaf pans.\n" +
                "\n" +
                "Step 2\n" +
                "Combine sugar, flour, eggs, shortening, whole milk, vanilla extract, lemon extract, and baking powder in a large bowl; pour into the prepared pans.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven until a toothpick inserted in the center of the cake comes out clean, about 30 minutes.", R.drawable.oldfashionedlemonpoundcake))
        foodlist.add(Food("Sour Cream Lemon Pound Cake", "Super moist, refreshing dessert. I serve this at Easter or Mother's Day. I store the compote separately and let it cool slightly before I serve.\n" +
                "\n" +
                "Prep: 40 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 1 hr 10 mins\n" +
                "Total: 2 hrs 50 mins\n" +
                "Servings: 16\n" +
                "Yield: 2 loaf cakes\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "\n" +
                "FOR THE CAKE:\n" +
                "~ cooking spray\n" +
                "~ 3 ¼ cups all-purpose flour\n" +
                "~ ½ teaspoon baking powder\n" +
                "~ ¼ teaspoon salt\n" +
                "~ ¾ cup butter\n" +
                "~ 2 ½ cups white sugar\n" +
                "~ 2 teaspoons lemon extract\n" +
                "~ 1 tablespoon lemon zest\n" +
                "~ 1 teaspoon lemon zest\n" +
                "~ 2 tablespoons lemon juice\n" +
                "~ 3 eggs, room temperature\n" +
                "~ 2 cups sour cream\n" +
                "\n" +
                "FOR THE COMPOTE:\n" +
                "~ 4 cups frozen pitted sweet cherries\n" +
                "~ ¼ cup white sugar\n" +
                "~ 2 tablespoons cold water\n" +
                "~ 2 teaspoons cornstarch\n" +
                "~ ¼ teaspoon almond extract\n" +
                "\n" +
                "FOR THE GLAZE:\n" +
                "~ 2 tablespoons lemon juice\n" +
                "~ 1 cup confectioners' sugar\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat an oven to 350 degrees F (175 degrees C). Spray 2 loaf pans with cooking spray.\n" +
                "\n" +
                "Step 2\n" +
                "Combine flour, baking powder, and salt in a bowl; set aside.\n" +
                "\n" +
                "Step 3\n" +
                "Beat butter, 2 1/2 cups sugar, and lemon extract with an electric mixer in a large bowl until light and fluffy and noticeably lighter in color. Stir in lemon zest and 2 tablespoons lemon juice; beat for 30 seconds. Add eggs one at a time, beating well after each addition.\n" +
                "\n" +
                "Step 4\n" +
                "Add the flour mixture alternately with the sour cream, mixing until just incorporated. Spoon the batter into the prepared pans.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven until a toothpick inserted into the center comes out clean, about 1 hour. Cool in the pans for 10 minutes before removing to cool completely on a wire rack.\n" +
                "\n" +
                "Step 6\n" +
                "Meanwhile, combine cherries, 1/4 cup sugar, water, and cornstarch for the compote in a saucepan. Bring to a boil over medium-high heat. Cook for 1 minute, stirring constantly. Remove from the heat and stir in the almond extract. Set aside.\n" +
                "\n" +
                "Step 7\n" +
                "Combine lemon juice and confectioners' sugar; mix well. When the cake is completely cool, drizzle the lemon glaze over top. Serve with cherry compote.", R.drawable.sourcreamlemonpoundcakewithcherrycompote))
        foodlist.add(Food("Yogurt Cake", "A lemon pound cake made with yogurt instead of sour cream. I have used this recipe for a long time and had misplaced it. I went to my daughter and had her give me another copy.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 10 mins\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 10-inch Bundt pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup butter, room temperature\n" +
                "~ 2 cups white sugar\n" +
                "~ 3 eggs, room temperature\n" +
                "~ 1 teaspoon lemon extract\n" +
                "~ 2 ¼ cups all-purpose flour\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ½ teaspoon salt\n" +
                "~ 1 (8 ounce) container lemon flavored yogurt\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 325 degrees F (165 degrees C). Grease and flour a 10-inch bundt pan.\n" +
                "\n" +
                "Step 2\n" +
                "Beat butter and sugar together in a large bowl with an electric mixer until light and fluffy. Beat one egg at a time into the butter mixture; add lemon extract with last egg.\n" +
                "\n" +
                "Step 3\n" +
                "Sift together the flour, baking soda and salt. Alternately mix in the flour mixture and the yogurt, starting and ending with the dry ingredients. Beat just until incorporated.\n" +
                "\n" +
                "Step 4\n" +
                "Pour batter into prepared pan. Bake in the preheated oven for 60 minutes, or until a toothpick inserted into the center of the cake comes out clean. Allow to cool 10 minutes in the pan, then turn out onto a wire rack and cool completely.", R.drawable.yogurtcake))
        foodlist.add(Food("Lemon Pound Cake Cookies", "I was trying to make lemon cookies, but ended up making something totally different and I was glad with the outcome.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 10 mins\n" +
                "Total: 25 mins\n" +
                "Servings: 14\n" +
                "Yield: 28 cookies\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ¾ cup white sugar\n" +
                "~ ⅔ cup salted sweet cream butter, softened\n" +
                "~ 2 cups all-purpose flour\n" +
                "~ 2 teaspoons baking powder\n" +
                "~ 2 eggs\n" +
                "~ 2 teaspoons lemon extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 400 degrees F (200 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Cream sugar and butter together in a bowl using an electric mixer until light and fluffy. Whisk flour and baking powder together in a separate bowl; gradually beat into creamed butter mixture until just combined. Beat eggs, 1 at a time, into butter-flour mixture until dough is thoroughly mixed; stir in lemon extract. Drop small scoops of dough onto a baking sheet.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven until cookies spring back when touched, 10 to 12 minutes.", R.drawable.lemonpoundcakecookies))
        foodlist.add(Food("Lemon Pecan Pound Cake", "This recipe makes a delicious lemon-flavored pound cake with chopped pecans and a simple lemon glaze.\n" +
                "\n" +
                "Servings: 14\n" +
                "Yield: 1 tube cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup butter\n" +
                "~ 2 cups confectioners' sugar\n" +
                "~ 3 eggs\n" +
                "~ 1 ½ cups cake flour\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 tablespoon lemon zest\n" +
                "~ ½ cup chopped pecans\n" +
                "~ 1 cup sifted confectioners' sugar\n" +
                "~ 2 tablespoons fresh lemon juice\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a large bowl, cream butter and 2 cups confectioners' sugar until fluffy. Beat in eggs one at a time, beating well after each addition. Stir in flour and vanilla, then pecans and lemon peel. Turn batter into greased 9 inch tube pan.\n" +
                "\n" +
                "Step 2\n" +
                "Bake at 325 degrees F (165 degrees C) for 40 to 45 minutes, or until done. Cool. Remove cake from pan, and invert onto serving plate.\n" +
                "\n" +
                "Step 3\n" +
                "Make glaze by mixing 1 cup sifted confectioners' sugar with fresh lemon juice. Drizzle over cake.", R.drawable.lemonpecanpoundcake))
        foodlist.add(Food("Lemon-Buttermilk Pound ", "Prep: 20 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 10 mins\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 pound cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "\n" +
                "CAKE:\n" +
                "~ 2 ½ cups white sugar\n" +
                "~ 1 ½ cups butter, softened\n" +
                "~ 4 eggs\n" +
                "~ 3 ½ cups all-purpose flour\n" +
                "~ ½ teaspoon salt\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ 1 cup buttermilk\n" +
                "~ 1 teaspoon lemon extract\n" +
                "\n" +
                "GLAZE:\n" +
                "~ 2 cups confectioners' sugar\n" +
                "~ ¼ cup lemon juice\n" +
                "~ 2 tablespoons butter, softened\n" +
                "~ 1 tablespoon lemon zest\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour a fluted tube pan (such as Bundt).\n" +
                "\n" +
                "Step 2\n" +
                "Beat white sugar and 1 1/2 cups butter together in a bowl with an electric mixer until light and fluffy, about 10 minutes. Add eggs one at a time, thoroughly beating each egg into the butter mixture before adding the next.\n" +
                "\n" +
                "Step 3\n" +
                "Sift flour, salt, and baking soda together in a bowl. Add 1/3 of the flour mixture to the butter mixture; mix well. Pour in 1/2 the buttermilk and beat until combined. Repeat adding the remaining flour mixture and buttermilk, beating well after each addition, and ending with the flour mixture. Stir lemon extract into batter. Pour batter into prepared tube pan.\n" +
                "\n" +
                "Step 4\n" +
                "Reduce oven temperature to 325 degrees F (165 degrees C).\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the oven until a toothpick inserted into the center of the cake comes out clean, 60 to 75 minutes. Cool in the pan for 10 minutes before removing to a cake platter or plate.\n" +
                "\n" +
                "Step 6\n" +
                "Beat confectioner's sugar, lemon juice, 2 tablespoons butter, and lemon zest together in a bowl until glaze is smooth. Pour about half the glaze over the cake; let cool. Pour remaining glaze over the cake.", R.drawable.lemonbuttermilkpoundcakewithauntevelynslemonglaze))
        foodlist.add(Food("Glazed Lemon Supreme Cake", "The use of lemon cake mix, lemon gelatin, lemon extract and fresh lemon juice make this cake a lemon-lover's dream. It's also amazingly easy.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 16\n" +
                "Yield: 1 - 10 inch tube pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (18.25 ounce) package lemon cake mix\n" +
                "~ 1 (3 ounce) package lemon flavored Jell-O® mix\n" +
                "~ 1 teaspoon lemon extract\n" +
                "~ ¾ cup apricot nectar\n" +
                "~ 4 eggs\n" +
                "~ ½ cup vegetable oil\n" +
                "~ ¼ cup lemon juice\n" +
                "~ 1 ½ cups confectioners' sugar\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour a 10 inch tube pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, combine cake mix, gelatin, lemon extract, apricot nectar, eggs and oil. Mix to combine, then beat on high speed for 3 minutes. Pour batter into a 10 inch tube pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven for 60 minutes, or until a toothpick inserted into the cake comes out clean.\n" +
                "\n" +
                "Step 4\n" +
                "While cake is baking, make the glaze: In a small bowl, combine lemon juice and confectioners sugar; stir until smooth. Remove cake from oven, and with cake still in pan, pour glaze over top of hot cake, tipping pan to allow excess glaze to run down sides of pan. Allow cake to cool in pan 10 minutes. Remove from pan and cool completely on wire rack.", R.drawable.glazedlemonsupremepoundcake))
        foodlist.add(Food("Easter Lemon Bread", "This was my grandmother's recipe. Don't know why they call it bread; it's a lemon pound cake. Every time I make it, I am always asked for the recipe.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 10 mins\n" +
                "Total: 1 hr 40 mins\n" +
                "Servings: 20\n" +
                "Yield: 2 loaves\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 3 cups all-purpose flour\n" +
                "~ 2 teaspoons baking powder\n" +
                "~ 2 teaspoons salt\n" +
                "~ ½ cup butter, room temperature\n" +
                "~ 2 ½ cups white sugar\n" +
                "~ 4 eggs, room temperature\n" +
                "~ ½ teaspoon almond extract\n" +
                "~ 1 cup milk, room temperature\n" +
                "~ 2 tablespoons grated lemon zest\n" +
                "~ 1 cup chopped walnuts\n" +
                "~ 6 tablespoons lemon juice\n" +
                "~ ½ cup white sugar\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 325 degrees F (165 degrees C). Grease and flour 2 loaf pans.\n" +
                "\n" +
                "Step 2\n" +
                "Combine the flour, baking powder, and salt and set aside.\n" +
                "\n" +
                "Step 3\n" +
                "Beat the butter and 2 1/2 cups sugar with an electric mixer in a large bowl until light and fluffy. The mixture should be noticeably lighter in color. Add the room-temperature eggs one at a time, allowing each egg to blend into the butter mixture before adding the next. Beat in the almond extract with the last egg.\n" +
                "\n" +
                "Step 4\n" +
                "Pour in the flour mixture alternately with the milk, mixing until just incorporated. Fold in the lemon zest and chopped walnuts, mixing just enough to evenly combine. Pour the batter into the prepared pans.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven until the loaves are very lightly browned and a toothpick inserted into the middle comes out clean, 60 to 70 minutes.\n" +
                "\n" +
                "Step 6\n" +
                "Mix the lemon juice with the 1/2 cup sugar in a bowl until the sugar has dissolved. Pour the lemon glaze over the hot cakes; allow to stand for 10 minutes before removing cakes from the pans. Cool before serving.", R.drawable.easterlemonbread))
        foodlist.add(Food("1 Egg Lemon Pound Cake", " Household favorite.\n" +
                "\n" +
                "Servings: 10\n" +
                "Yield: 1 - 8x4 inch loaf pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 cups all-purpose flour\n" +
                "~ 2 teaspoons baking powder\n" +
                "~ 1 cup white sugar\n" +
                "~ 1 teaspoon salt\n" +
                "~ ½ cup butter\n" +
                "~ 1 egg\n" +
                "~ 1 cup milk\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 teaspoon lemon extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C) grease and flour an 8x4 inch loaf pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, mix flour, baking powder, sugar and salt. Cut in butter. Break 1 egg into a measuring cup and then fill to the 1 cup line with milk. Add to dry ingredients and beat well. Add vanilla and lemon flavoring.\n" +
                "\n" +
                "Step 3\n" +
                "Pour batter into an 8x4 inch loaf pan. Bake at 350 degrees F (175 degrees C) for 30 minutes or until a toothpick inserted into the center of cake comes out clean.", R.drawable.oneegglemonpoundcake))
        foodlist.add(Food("Rainbow Cupcakes", "Rainbow cupcakes or cake, taste the same as normal cupcakes, but more fun to eat! Perfect for kids and people of all ages!\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 15 mins\n" +
                "Total: 35 mins\n" +
                "Servings: 24\n" +
                "Yield: 24 cupcakes\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 ½ cups all-purpose flour\n" +
                "~ 2 teaspoons baking powder\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ½ teaspoon salt\n" +
                "~ ½ cup milk\n" +
                "~ ½ cup vegetable oil\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ ½ cup butter\n" +
                "~ 1 cup white sugar\n" +
                "~ 3 eggs, room temperature\n" +
                "~ red food coloring\n" +
                "~ blue food coloring\n" +
                "~ green food coloring\n" +
                "~ yellow food coloring\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat an oven to 350 degrees F (175 degrees C). Line two 12 cup muffin pans with paper baking cups. Stir together the flour, baking powder, baking soda, and salt in a large bowl. Whisk together the milk, vegetable oil, and vanilla extract in a separate bowl until evenly blended; set aside.\n" +
                "\n" +
                "Step 2\n" +
                "Beat the butter and sugar with an electric mixer in a large bowl until light and fluffy. The mixture should be noticeably lighter in color. Add the room-temperature eggs one at a time, allowing each egg to blend into the butter mixture before adding the next. Pour in the flour mixture alternately with the milk mixture, mixing until just incorporated.\n" +
                "\n" +
                "Step 3\n" +
                "Divide the cake batter into four separate bowls. Add a few drops of food coloring into one bowl of batter and stir; add more food coloring, if necessary, to reach the desired shade. Repeat with the remaining colors and bowls of batter.\n" +
                "\n" +
                "Step 4\n" +
                "Using a different spoon for each color batter, spoon a small spoonful of each color into the cupcake liners, until 1/2 to 3/4 full. Do not mix the batter once it is in the cupcake liner. Bake in the preheated oven until a toothpick inserted into the cake comes clean, about 15 to 20 minutes.", R.drawable.rainbowcupcakes))
        foodlist.add(Food("Rainbow Citrus Cake", "Tri-colored cake with a lemon layer, lime layer and an orange layer. The filling is a lemon curd and the frosting is orange.\n" +
                "\n" +
                "Prep: 45 mins\n" +
                "Cook: 30 mins\n" +
                "Additional: 1 hr 30 mins\n" +
                "Total: 2 hrs 45 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 3-layer 9-inch cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 3 ½ cups all-purpose flour\n" +
                "~ 5 teaspoons baking powder\n" +
                "~ 1 teaspoon salt\n" +
                "~ ¾ cup shortening\n" +
                "~ 2 ¼ cups white sugar\n" +
                "~ 4 eggs, room temperature\n" +
                "~ 1 ½ cups milk\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 2 teaspoons grated lemon zest\n" +
                "~ 2 teaspoons grated orange zest\n" +
                "~ 2 teaspoons grated lime zest\n" +
                "~ 2 drops yellow food coloring\n" +
                "~ 2 drops orange food coloring\n" +
                "~ 2 drops green food coloring\n" +
                "~ 1 recipe Lemon Custard Filling\n" +
                "~ 1 recipe Orange Cream Frosting\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour three 9-inch pans. Sift together the flour, baking powder, and salt. Set aside.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, cream together the shortening and sugar until light and fluffy. Beat in the eggs one at a time, mixing until each egg is incorporated; stir in the vanilla. Beat in the flour mixture alternately with the milk. Divide batter into 3 bowls.\n" +
                "\n" +
                "Step 3\n" +
                "In the first bowl, stir in lemon zest and yellow food coloring; pour into prepared pan. In the second bowl, stir in orange zest and orange food coloring; pour into second prepared pan. In the last bowl, stir in the lime zest and green food coloring; pour into third prepared pan.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven until a toothpick inserted into the center of each cake layer comes out clean, about 30 minutes. Let cool in pan for 5 minutes, then turn out onto a wire rack and cool completely.\n" +
                "\n" +
                "Step 5\n" +
                "Assemble the cake: stack the layers together with the Lemon Filling in between the layers. Frost sides and top with Orange Cream Frosting. Refrigerate until serving.", R.drawable.rainbowcitruscake))
        foodlist.add(Food("Rainbow Bundt Cake", "I wanted something unique and fun for my grandson's birthday. I initially thought about doing a rainbow layer cake, but thought I might be able to achieve an actual rainbow arc if I used a fluted tube pan (such as Bundt®). I was thrilled when we sliced this and I saw how vibrant and arc-shaped the rainbow turned out. And my little guy was delighted with it ... he thought I was magic!\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 35 mins\n" +
                "Additional: 1 hr 10 mins\n" +
                "Total: 2 hrs 15 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 tablespoons all-purpose flour, or as needed\n" +
                "~ 1 package white cake mix\n" +
                "~ 1 (1 ounce) package instant vanilla pudding mix\n" +
                "~ 4 eggs, lightly beaten\n" +
                "~ 1 cup sour cream\n" +
                "~ ½ cup butter, melted\n" +
                "~ ½ cup water\n" +
                "~ 1 teaspoon white sugar\n" +
                "~ 1 drop red paste food coloring, or as needed\n" +
                "~ 1 drop purple gel food coloring, or as needed\n" +
                "~ 1 drop blue paste food coloring, or as needed\n" +
                "~ 1 drop green paste food coloring, or as needed\n" +
                "~ 1 drop yellow paste food coloring, or as needed\n" +
                "~ 1 drop orange paste food coloring, or as needed\n" +
                "~ 1 (16 ounce) package prepared chocolate frosting\n" +
                "~ ¼ cup rainbow sprinkles, or as needed\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Grease and flour a 10-cup fluted tube pan.\n" +
                "\n" +
                "Step 2\n" +
                "Combine cake mix, pudding mix, eggs, sour cream, butter, water, and sugar in a mixing bowl. Beat on low speed until ingredients are moistened, then beat on medium-high speed for 1 minute. Batter will be thick. Divide batter evenly into 6 bowls. Tint each batch a different color, using enough food coloring paste to reach desired tone; colors will deepen and brighten when baked.\n" +
                "\n" +
                "Step 3\n" +
                "Spoon red batter into the bottom of the prepared pan and smooth using an offset spatula to evenly distribute. Carefully spoon purple batter on top and spread with spatula, pressing batter thinner near the inner edge and thicker at the outer edge of pan to create an arc effect. Repeat with blue, green, yellow, and orange batters. Gently tap pan on the counter 3 times to allow batter to settle.\n" +
                "\n" +
                "Step 4\n" +
                "Bake on the middle rack of the preheated oven until a toothpick inserted in the center comes out clean and top is just starting to brown, 35 to 45 minutes. Be careful not to overbake.\n" +
                "\n" +
                "Step 5\n" +
                "Cool cake in the pan for 10 minutes. Turn cake out onto a plate and allow to cool completely before frosting, about 1 hour.\n" +
                "\n" +
                "Step 6\n" +
                "Drizzle or spread frosting over cake and decorate with rainbow sprinkles.", R.drawable.rainbowbundtcake))
        foodlist.add(Food("Rainbow Clown Cake", "I went a little overboard creating a fun confetti-ish cake for a student teacher and deemed the result 'Clown Vomit Cake.' Despite its nickname, it's a popular request now with my colleagues! Frost with your favorite frosting and top with colored sugar.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 30 mins\n" +
                "Total: 1 hr\n" +
                "Servings: 8\n" +
                "Yield: 1 8-inch layer cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 3 egg whites\n" +
                "~ 1 (18.25 ounce) package white cake mix\n" +
                "~ 1 ⅓ cups water\n" +
                "~ 2 tablespoons canola oil\n" +
                "~ red paste food coloring\n" +
                "~ orange paste food coloring\n" +
                "~ yellow paste food coloring\n" +
                "~ green paste food coloring\n" +
                "~ blue paste food coloring\n" +
                "~ purple paste food coloring\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat an oven to 350 degrees F (175 degrees C). Grease and flour two 8-inch round cake pans.\n" +
                "\n" +
                "Step 2\n" +
                "Beat the egg whites with an electric mixer until frothy, about 1 minute. Add the cake mix, water, and canola oil; continue beating for 2 minutes on medium speed.\n" +
                "\n" +
                "Step 3\n" +
                "Divide the cake batter into six separate bowls. Use a toothpick to scoop a dab of food coloring into one bowl of batter and stir; add more food coloring, if necessary, to reach the desired shade. Repeat with the remaining colors and bowls of batter.\n" +
                "\n" +
                "Step 4\n" +
                "Scoop spoonfuls of batter into the prepared pans, alternating the colors. Use a toothpick to gently swirl the colors for a marbled effect.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven until a toothpick inserted into the cake comes clean, about 30-35 minutes. Cool in the pans for 10 minutes before removing to cool completely on a wire rack.", R.drawable.rainbowclowncake))
        foodlist.add(Food("Epic Rainbow Cake", "Easier than it sounds! This six-layer rainbow cake looks like your normal everyday dessert, but after one slice everyone will see its awesomeness. I've never seen a dessert get so much camera attention!\n" +
                "\n" +
                "\n" +
                "Prep: 45 mins\n" +
                "Cook: 1 hr 15 mins\n" +
                "Additional: 30 mins\n" +
                "Total: 2 hrs 30 mins\n" +
                "Servings: 16\n" +
                "Yield: 1 9-inch layer cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ cooking spray\n" +
                "~ 3 (18.25 ounce) packages white pudding-type cake mix\n" +
                "~ 9 egg whites\n" +
                "~ 4 cups water\n" +
                "~ 1 cup applesauce\n" +
                "~ 2 (16 ounce) cans white frosting\n" +
                "~ ½ fluid ounce red gel food coloring, or as desired\n" +
                "~ ½ fluid ounce orange gel food coloring, or as desired\n" +
                "~ ½ fluid ounce yellow gel food coloring, or as desired\n" +
                "~ ½ fluid ounce green gel food coloring, or as desired\n" +
                "~ ½ fluid ounce blue gel food coloring, or as desired\n" +
                "~ ½ fluid ounce purple gel food coloring, or as desired\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Spray two 9-inch cake pans with cooking spray.\n" +
                "\n" +
                "Step 2\n" +
                "Combine cake mix, egg whites, water, and applesauce in a large bowl using an electric mixer. Divide batter evenly into 6 bowls. Mix a different food coloring gel into each bowl. Pour the red batter and orange batter separately into the prepared cake pans.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven until a toothpick inserted into the center of each cake comes out clean, 25 to 30 minutes.\n" +
                "\n" +
                "Step 4\n" +
                "Remove from the oven and let rest on a cooling rack in the pans for 15 minutes. Flip quickly onto the rack and remove cakes from the pans. Continue the baking and cooling process with remaining batter until all layers are cooled.\n" +
                "\n" +
                "Step 5\n" +
                "Shave off the tops of the cakes carefully using a large knife so they will be flat. Place the red layer down, frost the top lightly; continue with orange, yellow, green, blue, and purple.\n" +
                "\n" +
                "Step 6\n" +
                "Frost the top and outside of the cake. Cut through using a big, sharp knife and serve.", R.drawable.epicrainbowcake))
        foodlist.add(Food("Rainbow Cupcake Cones", "It's a cupcake inside of a wafer ice cream cone! Use your favorite flavor of frosting and toppings. I have no doubt they will be a favorite for you as well...enjoy.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 20 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 1 hr 40 mins\n" +
                "Servings: 24\n" +
                "Yield: 24 cupcakes\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (18.25 ounce) package white cake mix\n" +
                "~ 1 cup water\n" +
                "~ 3 eggs\n" +
                "~ ⅓ cup vegetable oil\n" +
                "~ 1 drop red food coloring, or desired amount\n" +
                "~ 1 drop green food coloring, or desired amount\n" +
                "~ 1 drop yellow food coloring, or desired amount\n" +
                "~ 1 drop blue food coloring, or desired amount\n" +
                "~ 24 flat-bottomed wafer ice cream cones in assorted colors\n" +
                "~ 1 (16 ounce) container prepared vanilla frosting\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Stir cake mix, water, eggs, and vegetable oil together in a bowl until moistened; beat the batter with an electric mixer set on medium speed for 2 minutes.\n" +
                "\n" +
                "Step 3\n" +
                "Divide cake batter into 4 equal portions in separate bowls. Tint each bowl a different color, stirring in red, green, yellow, and blue food coloring to reach desired shade.\n" +
                "\n" +
                "Step 4\n" +
                "Set ice cream cones upright in 24 muffin cups.\n" +
                "\n" +
                "Step 5\n" +
                "Spoon about 1 1/2 tablespoons of each colored batter into cones, leaving about 1 inch of room from the top.\n" +
                "\n" +
                "Step 6\n" +
                "Carefully transfer cones to the preheated oven and bake until a toothpick inserted into the middle of a filled cone comes out clean, 20 to 25 minutes.\n" +
                "\n" +
                "Step 7\n" +
                "Cool cupcakes thoroughly before decorating, about 1 hour.", R.drawable.rainbowcupcakecones))
        foodlist.add(Food("6 Layer Rainbow Cake", "If you want to make a homemade rainbow cake from scratch, this is your recipe! Bake every color of the rainbow into this delightful cake, with one color for every cake layer. This cake recipe makes a six-layer, 9-inch round cake and comes with a light mascarpone cream frosting.\n" +
                "\n" +
                "Prep: 10 mins\n" +
                "Cook: 1 hr 30 mins\n" +
                "Additional: 2 hrs\n" +
                "Total: 3 hrs 40 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 rainbow cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 3 cups all-purpose flour\n" +
                "~ 3 teaspoons baking powder\n" +
                "~ 1 teaspoon baking soda\n" +
                "~ ¾ cup unsalted butter, softened\n" +
                "~ 2 tablespoons unsalted butter, softened\n" +
                "~ 1 ⅓ cups milk\n" +
                "~ 1 cup white sugar\n" +
                "~ 5 large egg whites\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ food coloring in 6 rainbow colors (red, orange, yellow, green, blue, violet)\n" +
                "\n" +
                "FROSTING\n" +
                "~ 2 ½ cups heavy whipping cream\n" +
                "~ ⅓ cup confectioners' sugar\n" +
                "~ 2 (8 ounce) containers mascarpone cheese\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 400 degrees F (200 degrees C). Grease six 9-inch round cake pans and dust with flour.\n" +
                "\n" +
                "Step 2\n" +
                "Sift together the flour, baking powder, and baking soda.\n" +
                "\n" +
                "Step 3\n" +
                "Beat 3/4 cup plus 2 tablespoons butter, milk, sugar, egg whites, and vanilla extract in a large bowl with an electric mixer until smooth. Stir in flour mixture and mix until just combined.\n" +
                "\n" +
                "Step 4\n" +
                "Divide cake batter evenly between 6 bowls. Mix in different food coloring to each bowl, creating 6 different colored batters. Start with just 1 to 2 drops of food coloring in each bowl, then mix; you can always add more.\n" +
                "\n" +
                "Step 5\n" +
                "Bake each cake in the preheated oven, until a toothpick inserted in the center comes out clean, 15 to 20 minutes. Set aside to cool.\n" +
                "\n" +
                "Step 6\n" +
                "Beat cream in a chilled glass or metal bowl with an electric mixer until stiff peaks form. Stir together confectioners' sugar and mascarpone cheese in a separate bowl, then fold gently into the whipped cream until smooth.\n" +
                "\n" +
                "Step 7\n" +
                "Spread frosting on each layer of cooled cake, placing one on top of the other. Cover the top and sides with the remaining frosting. Refrigerate for 2 hours before serving.", R.drawable.sixlayerrainbowcake))
        foodlist.add(Food("Over the Rainbow Cupcakes", "These rainbow cupcakes from scratch make for a psychedelic and delicious treat! Kids will love the way they look and adults will love the moist texture and delicious flavor. Perfect for birthday parties and bake sales. Eat these plain or decorate with a vanilla buttercream frosting and you won't go wrong!\n" +
                "\n" +
                "Prep: 1 hr\n" +
                "Cook: 20 mins\n" +
                "Total: 1 hr 20 mins\n" +
                "Servings: 18\n" +
                "Yield: 18 cupcakes\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 ¾ cups all-purpose flour\n" +
                "~ 4 teaspoons baking powder\n" +
                "~ ¾ teaspoon salt\n" +
                "~ 4 large egg whites\n" +
                "~ 1 ½ cups sugar, divided\n" +
                "~ ¾ cup unsalted butter, softened\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 ½ cups milk\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ rainbow food colorings (red, orange, yellow, blue, green, purple)\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (180 degrees C). Line 18 muffin cups with paper liners.\n" +
                "\n" +
                "Step 2\n" +
                "Sift flour, baking powder, and salt together three times. Set aside.\n" +
                "\n" +
                "Step 3\n" +
                "Beat egg whites in a bowl until foamy. Gradually add 1/2 cup sugar and continue beating just until soft peaks form.\n" +
                "\n" +
                "Step 4\n" +
                "Beat butter in a separate bowl until fluffy, 2 to 3 minutes. Gradually add remaining 1 cup sugar and cream and mix until light and fluffy, about 5 minutes. Add sifted dry ingredients alternately with milk a small amount at a time, beating after each addition. Mix in vanilla. Add egg whites and mix well.\n" +
                "\n" +
                "Step 5\n" +
                "Set out 6 bowls. Add 1 drop of food coloring to the bowls, using a different color in each bowl. Divide the cupcake batter evenly between the bowls. Stir until the coloring is evenly distributed throughout the batter. Add more food coloring, a drop at a time, until desired color is reached.\n" +
                "\n" +
                "Step 6\n" +
                "Using a tablespoon, spoon the first colored batter into the bottom of each cupcake liner in the prepared pan. Gently spread out to the edge of the liner to create an even layer. Repeat with remaining colors.\n" +
                "\n" +
                "Step 7\n" +
                "Bake in the preheated oven a toothpick inserted into the center of a cupcake comes out clean, about 20 minutes. Cool slightly in pan, then remove to cool completely on wire racks.", R.drawable.overtherainbowcupcakes))
        foodlist.add(Food("Apple Frangipane Cake", "This is a dense, moist, and very flavorful apple frangipane cake that is very almond-forward in a yummy way. The apples complement it nicely and I loved the crunch of the toasted almonds on top.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 50 mins\n" +
                "Additional: 1 hr 10 mins\n" +
                "Total: 2 hrs 15 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 9-inch cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ½ cup unsalted butter, softened\n" +
                "~ 6 tablespoons unsalted butter, softened\n" +
                "~ 1 (7 ounce) package almond paste\n" +
                "~ 1 cup white sugar\n" +
                "~ 1 teaspoon vanilla bean paste\n" +
                "~ 5 large eggs\n" +
                "~ 1 cup sifted cake flour\n" +
                "~ ½ teaspoon kosher salt\n" +
                "~ 2 medium Apples, raw\n" +
                "~ 1 tablespoon unsalted butter, melted\n" +
                "~ 1 tablespoon white sugar\n" +
                "~ ¼ cup powdered sugar, or to taste\n" +
                "~ 2 tablespoons toasted sliced almonds, or to taste\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Grease and flour a 9-inch round springform pan.\n" +
                "\n" +
                "Step 2\n" +
                "Beat 1/2 cup plus 6 tablespoons softened butter, almond paste, and 1 cup sugar at low speed with a stand mixer fitted with the paddle attachment, until combined. Increase speed to medium-high and beat until smooth, light, and fluffy, 3 to 4 minutes. Beat in vanilla bean paste; add eggs, 1 at a time, and beat until completely combined after each addition. Gently fold in flour and salt with a rubber spatula until just combined.\n" +
                "\n" +
                "Step 3\n" +
                "Spoon mixture into the prepared springform pan. Arrange apples slices over top of batter; brush apples with melted butter and sprinkle with 1 tablespoon sugar.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven until browned and a wooden pick inserted in the center comes out clean, 50 to 55 minutes. Remove to a wire rack and cool for 10 minutes. Remove sides of pan and cool completely, about 1 hour. Dust top of cake with powdered sugar and sprinkle with toasted sliced almonds.", R.drawable.applefrangipanecake))
        foodlist.add(Food("Smith Island Cake", "Really tender cake layers with buttery vanilla flavor. A lightly sweet ganache icing helps balance out the whole thing. Prepare this showstopper for a special occasion and receive rave reviews. Using aluminum pans really speeds up the assembly and cooking process. The batter holds well while the other layers cook.\n" +
                "\n" +
                "Prep: 45 mins\n" +
                "Cook: 45 mins\n" +
                "Additional: 4 hrs 15 mins\n" +
                "Total: 5 hrs 45 mins\n" +
                "Servings: 16\n" +
                "Yield: 1 8 1/2-inch layer cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "\n" +
                "ICING:\n" +
                "~ 4 cups heavy cream\n" +
                "~ ¼ cup light corn syrup\n" +
                "~ 24 ounces bittersweet baking chocolate, chopped\n" +
                "~ ¼ teaspoon kosher salt\n" +
                "\n" +
                "CAKE:\n" +
                "~ cooking spray\n" +
                "~ 1 ½ cups salted butter, softened\n" +
                "~ 2 cups white sugar\n" +
                "~ 5 large eggs, at room temperature\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 3 ¾ cups all-purpose flour, sifted\n" +
                "~ 1 ½ teaspoons baking powder\n" +
                "~ ¾ teaspoon baking soda\n" +
                "~ ½ teaspoon kosher salt\n" +
                "~ 1 ¾ cups buttermilk, at room temperature\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Prepare icing: Combine cream and corn syrup in a medium saucepan over medium heat; bring mixture just to a simmer, stirring often. Remove from the heat.\n" +
                "\n" +
                "Step 2\n" +
                "Place chocolate in a large microwave-safe bowl and pour hot cream mixture over top. Sprinkle with salt and let stand 2 minutes. Whisk until completely smooth and let cool to room temperature, about 1 hour. Chill icing in the refrigerator until thickened and spreadable, about 45 minutes, stirring halfway through.\n" +
                "\n" +
                "Step 3\n" +
                "Meanwhile prepare cake layers: Preheat the oven to 350 degrees F (175 degrees C). Spray nine 8 1/2-inch round disposable cake pans with cooking spray. Line the bottoms with parchment paper and spray that gently with more cooking spray.\n" +
                "\n" +
                "Step 4\n" +
                "Beat butter in a stand mixer fitted with the paddle attachment at medium speed until smooth and creamy, about 1 minute. Gradually add sugar and beat until light and fluffy, about 3 minutes. Add eggs, 1 at a time, and beat just until incorporated after each addition. Beat in vanilla.\n" +
                "\n" +
                "Step 5\n" +
                "Whisk together flour, baking powder, baking soda, and salt in a medium bowl. Add flour mixture to butter mixture alternately with buttermilk in 3 batches, beginning and ending with flour mixture and beating on medium speed.\n" +
                "\n" +
                "Step 6\n" +
                "Divide batter evenly (about 1 cup each) among prepared pans and smooth with an offset spatula. Bake, in batches, until a wooden pick inserted in center comes out clean, 12 to 14 minutes. Let cool in pans on wire rack 10 minutes. Turn cakes out onto wire racks to cool completely, about 20 minutes.\n" +
                "\n" +
                "Step 7\n" +
                "Place 1 cake layer on an 8-inch round cake board and spread with 1/3 cup icing using a small offset spatula. Top with another cake layer and spread with 1/3 cup icing. Repeat the process with remaining layers and icing. Coat the top and sides of the cake with a thin layer of icing. Chill cake for 1 hour and reserve remaining icing.\n" +
                "\n" + 
                "Step 8\n" +
                "Place chilled cake on a wire rack over a rimmed baking pan. Microwave reserved icing at medium (50%) power in 30-second intervals, stirring after each one, until smooth and pourable, 1 to 1 1/2 minutes. Pour icing in a slow, steady stream over the top of the cake, starting at the center and moving to the outer edges so it flows down the sides. Smooth out the sides and fill any gaps with a small offset spatula.\n" +
                "\n" +
                "Step 9\n" +
                "Chill cake until set, about 1 hour. Carefully transfer cake to a serving plate. Serve at room temperature.", R.drawable.smithislandcake))
        foodlist.add(Food("Green Tea Mousse", "Been looking for a no-bake Japanese-style green tea cheesecake for a long time? Well, this is the recipe created just for you..\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 1 min\n" +
                "Additional: 7 hrs\n" +
                "Total: 7 hrs 21 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 9-inch cheesecake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (4.8 ounce) package graham crackers, crushed\n" +
                "~ 2 tablespoons white sugar\n" +
                "~ 3 tablespoons unsalted butter, melted\n" +
                "~ 2 tablespoons green tea powder (matcha)\n" +
                "~ ½ cup warm water\n" +
                "~ 2 tablespoons unflavored gelatin\n" +
                "~ ½ cup cold water\n" +
                "~ 2 cups whipping cream\n" +
                "~ 2 (8 ounce) packages cream cheese, at room temperature\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ ¼ cup honey\n" +
                "~ 2 eggs\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine the graham cracker crumbs with 2 tablespoons of sugar in a mixing bowl. Drizzle in the melted butter and mix until evenly moistened. Press into the bottom of a 9-inch springform pan lined with waxed paper; set aside.\n" +
                "\n" +
                "Step 2\n" +
                "Stir the tea powder into the warm water; set aside. Sprinkle the gelatin over the cold water; set aside.\n" +
                "\n" +
                "Step 3\n" +
                "Whip the cream to stiff peaks; set aside. Beat the cream cheese, 1/2 cup sugar, vanilla, and honey in a clean mixing bowl. Beat in the eggs one at a time until evenly blended. Cook the gelatin mixture in the microwave until melted, about 45 seconds. Beat the gelatin and tea into the cream cheese mixture, then fold in the whipped cream until smooth. Pour into the springform pan. Refrigerate 7 hours to overnight before unmolding and serving.", R.drawable.greenteamoussecheesecake))
        foodlist.add(Food("Cannoli", "Ana and Lydia's cannoli, recipe invented on July 31st, 2005. I spent a lot of time looking for a good recipe for cannoli shells and filling. Since no two were alike, and since instructions were a bit sketchy, I worked with a friend to come up with a good recipe, including some tips that we came up with along the way. Special equipment is needed such as cannoli tubes, a pasta machine and a pastry bag to help make these cannoli come out just like the ones at Italian restaurants and bakeries. Start with 1/2 cup of confectioners' sugar, and then add more to taste.\n" +
                "\n" +
                "Prep: 45 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 2 hrs\n" +
                "Total: 3 hrs 45 mins\n" +
                "Servings: 30\n" +
                "Yield: 30 cannoli\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "\n" +
                "SHELLS:\n" +
                "~ 3 cups all-purpose flour\n" +
                "~ ¼ cup white sugar\n" +
                "~ ¼ teaspoon ground cinnamon\n" +
                "~ 3 tablespoons shortening\n" +
                "~ 1 egg\n" +
                "~ 1 egg yolk\n" +
                "~ ½ cup sweet Marsala wine\n" +
                "~ 1 tablespoon distilled white vinegar\n" +
                "~ 2 tablespoons water\n" +
                "~ 1 egg white\n" +
                "~ 1 quart oil for frying, or as needed\n" +
                "\n" +
                "FILLING:\n" +
                "~ 1 (32 ounce) container ricotta cheese\n" +
                "~ ½ cup confectioners' sugar\n" +
                "~ 1 teaspoon lemon zest, or to taste\n" +
                "~ 4 ounces semisweet chocolate, chopped (Optional)\n" +
                "\n" +
                "\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a medium bowl, mix together the flour, sugar and cinnamon. Cut in the shortening until it is in pieces no larger than peas. Make a well in the center, and pour in the egg, egg yolk, Marsala wine, vinegar and water. Mix with a fork until the dough becomes stiff, then finish it by hand, kneading on a clean surface. Add a bit more water if needed to incorporate all of the dry ingredients. Knead for about 10 minutes, then cover and refrigerate for 1 to 2 hours.\n" +
                "\n" +
                "Step 2\n" +
                "Divide the cannoli dough into thirds, and flatten each one just enough to get through the pasta machine. Roll the dough through successively thinner settings until you have reached the thinnest setting. Dust lightly with flour if necessary. Place the sheet of dough on a lightly floured surface. Using a form or large glass or bowl, cut out 4 to 5 inch circles. Dust the circles with a light coating of flour. This will help you later in removing the shells from the tubes. Roll dough around cannoli tubes, sealing the edge with a bit of egg white.\n" +
                "\n" +
                "Step 3\n" +
                "Heat the oil to 375 degrees F (190 degrees C) in a deep-fryer or deep heavy skillet. Fry shells on the tubes a few at a time for 2 to 3 minutes, until golden. Use tongs to turn as needed. Carefully remove using the tongs, and place on a cooling rack set over paper towels. Cool just long enough that you can handle the tubes, then carefully twist the tube to remove the shell. Using a tea towel may help you get a better grip. Wash or wipe off the tubes, and use them for more shells. Cooled shells can be placed in an airtight container and kept for up to 2 months. You should only fill them immediately or up to 1 hours before serving.\n" +
                "\n" +
                "Step 4\n" +
                "To make the filling, stir together the ricotta cheese and confectioners' sugar using a spoon. Fold in lemon zest and chocolate. Use a pastry bag to pipe into shells, filling from the center to one end, then doing the same from the other side. Dust with additional confectioners' sugar and grated chocolate for garnish when serving.", R.drawable.cannoli))
        foodlist.add(Food("Portokalopita", "Portokalopita is a deliciously different cake, and a must-try for anyone who is afraid of phyllo. Why? Because here the phyllo is shredded to bits - the messier the better! The gorgeous orange and cinnamon syrup ensures this cake stays beautifully moist.\n" +
                "\n" +
                "Prep: 25 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 1 hr\n" +
                "Total: 2 hrs 25 mins\n" +
                "Servings: 12\n" +
                "Yield: 12 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "\n" +
                "SYRUP:\n" +
                "~ 2 cups white sugar\n" +
                "~ 1 ½ cups water\n" +
                "~ 1 teaspoon ground cinnamon\n" +
                "~ 1 orange, halved\n" +
                "\n" +
                "CAKE:\n" +
                "~ 1 (16 ounce) package phyllo dough\n" +
                "~ 3 oranges\n" +
                "~ 5 eggs\n" +
                "~ 1 (7 ounce) container Greek yogurt\n" +
                "~ ¾ cup olive oil, divided\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 tablespoon baking powder\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine 2 cups sugar, water, and cinnamon in a saucepan over medium-high heat. Squeeze in orange juice and add juiced halves. Bring to a boil and boil vigorously for 8 minutes. Remove from heat and allow to cool while you prepare the cake.\n" +
                "\n" +
                "Step 2\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Lightly grease a 9x13-inch baking pan with some olive oil.\n" +
                "\n" +
                "Step 3\n" +
                "Remove phyllo sheets from the package. Tear each sheet roughly into shreds and pile up in the baking pan. Let shredded phyllo dry out a little while you prepare the remaining ingredients. Cut 1 orange in half, and slice 1 half into very thin half-moons to garnish the cake. Zest and juice the remaining 2 1/2 oranges.\n" +
                "\n" +
                "Step 4\n" +
                "Combine orange juice, orange zest, eggs, yogurt, olive oil, 1/2 cup sugar, and baking powder in a blender or food processor. Blend together on high speed until frothy, about 2 minutes. Pour orange and egg mixture over the shredded phyllo in the baking pan. Stir everything together gently to ensure that the egg mixture is evenly distributed. Garnish top of the cake with the orange slices.\n" +
                "\n" +
                "Step 5\n" +
                "Bake in the preheated oven until the top is golden and the filling set, about 45 minutes. Remove from the oven and immediately pour the cooled syrup over the hot cake. Set aside for at least 1 hour, until most of the syrup has soaked in. Slice into squares and serve.", R.drawable.portokalopita))
        foodlist.add(Food("Chocolate Roll", "A chocolate cake is wrapped around alternating stripes of white chocolate whipped cream and a whipped dark chocolate mousse for not only great flavor, but a striking presentation as well. Make sure to really chill your fillings before whipping--overnight would be best--or they won't fluff up properly. This takes some time and patience to make--so plan ahead when making this cake! Serve with additional whipped cream, if desired.\n" +
                "\n" +
                "Prep: 1 hr\n" +
                "Cook: 15 mins\n" +
                "Additional: 6 hrs\n" +
                "Total: 7 hrs 15 mins\n" +
                "Servings: 10\n" +
                "Yield: 1 roll cake\n" +
                "\n" +
                "INGREDIENTS\n" +
                "\n" +
                "WHITE CHOCOLATE WHIP CREAM FILLING:\n" +
                "~ 1 ½ cups white chocolate chips (such as Ghirardelli\n" +
                "~ 1 ½ cups heavy cream, divided\n" +
                "\n" +
                "WHIPPED DARK CHOCOLATE MOUSSE FILLING:\n" +
                "~ 1 cup dark chocolate chips (such as Ghirardelli® 60% Cacao Baking Chips)\n" +
                "~ 1 ½ cups heavy cream, divided\n" +
                "\n" +
                "CHOCOLATE CAKE ROLL:\n" +
                "~ 4 egg yolks, at room temperature\n" +
                "~ ⅓ cup firmly packed dark brown sugar\n" +
                "~ 2 tablespoons unsalted butter, melted and cooled\n" +
                "~ 2 tablespoons strongly brewed black coffee, cooled\n" +
                "~ 1 ½ teaspoons vanilla extract\n" +
                "~ ½ cup all-purpose flour\n" +
                "~ ⅓ cup cocoa powder\n" +
                "~ ½ teaspoon baking powder\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ¼ teaspoon salt\n" +
                "~ 4 egg whites, at room temperature\n" +
                "~ ¼ cup white sugar\n" +
                "~ 1 tablespoon cocoa powder, or as needed\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Place white chocolate chips and 3/4 cup heavy cream for white chocolate filling in a medium microwave-safe bowl. Microwave on 50% power until chocolate is completely melted and cream is fully incorporated, about 2 minutes, stopping to stir every 30 seconds. Set aside and allow to cool to room temperature, 10 to 20 minutes. Place into the refrigerator until completely chilled, at least 4 hours, to overnight.\n" +
                "\n" +
                "Step 2\n" +
                "Meanwhile, place dark chocolate chips and 3/4 cup heavy cream for dark chocolate filling into another medium microwave-safe bowl. Microwave on 50% power until chocolate is melted and cream is fully incorporated, about 2 minutes, stopping to stir every 30 seconds. Set aside and allow to cool to room temperature, 10 to 20 minutes. Place into the refrigerator until completely chilled, at least 4 hours, to overnight.\n" +
                "\n" +
                "Step 3\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Line a 12x17-inch baking sheet with parchment paper. Grease the pan and parchment.\n" +
                "\n" +
                "Step 4\n" +
                "Combine egg yolks and brown sugar in a large bowl. Beat at medium-high speed until mixture is light and creamy, about 2 minutes. Mix in butter, coffee, and vanilla extract.\n" +
                "\n" +
                "Step 5\n" +
                "Sift flour, 1/3 cup cocoa, baking powder, baking soda, and salt together in a small bowl. Add to egg yolk mixture; mix until just combined.\n" +
                "\n" +
                "Step 6\n" +
                "Beat egg whites in a separate clean, dry, large bowl with clean, dry beaters on high speed until soft peaks form. Gradually add sugar, 1 tablespoon at a time, beating constantly. Continue to beat until mixture is glossy and stiff peaks form. Fold 1/3 of the egg white mixture gently into the yolk mixture until just combined. Fold in remaining egg whites gently until no streaks of egg white remain. Do not overmix.\n" +
                "\n" +
                "Step 7\n" +
                "Pour batter into the prepared pan and gently spread evenly.\n" +
                "\n" +
                "Step 8\n" +
                "Bake in the preheated oven until cake springs back lightly when touched, about 10 minutes. Do not overbake.\n" +
                "\n" +
                "Step 9\n" +
                "While cake is baking, prepare a piece of parchment paper that is slightly larger than the baking pan. Sprinkle lightly with remaining cocoa powder.\n" +
                "\n" +
                "Step 10\n" +
                "Remove cake from the oven and run a knife around the edges of the pan to loosen the cake. Invert the cake quickly and carefully, as pan will be hot, onto the cocoa-sprinkled parchment paper. Remove the piece of parchment the cake baked on and discard. Working quickly, roll the cake and cocoa-sprinkled paper up together, starting from the narrow end. Set the cake seam-side down on a wire rack to cool completely, 1 to 2 hours.\n" +
                "\n" +
                "Step 11\n" +
                "While cake cools, finish the fillings. Remove the white and dark chocolate from the refrigerator. The white chocolate will be soft-set. Whip the white chocolate, beginning on low speed and working up to medium-high, until light and fluffy, 1 to 2 minutes. Pour in remaining 3/4 cup of heavy cream, and beat on medium-high speed until mixture has the texture of whipped cream, 1 to 2 minutes. Place white chocolate whipped cream into a piping bag or zip-top bag with a 1/2-inch hole cut into a corner. Keep refrigerated until ready to use.\n" +
                "\n" +
                "Step 12\n" +
                "Prepare dark chocolate filling. The dark chocolate will be firm set. Whip the dark chocolate, starting on low speed and working up to medium speed, until mixture crumbles. Beat until the chocolate crumbles begin to become lighter in color and texture, 1 to 2 minutes. Add remaining 3/4 cup heavy cream and beat on medium-high speed until mixture is smooth, light, and fluffy, 1 to 2 minutes. The dark chocolate mixture will be slightly thicker than the white chocolate. Add more heavy cream if necessary to achieve the desired \"lightness\" and consistency of whipped mousse. Place whipped dark chocolate mousse into a piping bag or zip-top bag with a 1/2-inch hole cut into a corner. Keep refrigerated until ready to use.\n" +
                "\n" +
                "Step 13\n" +
                "Unroll cooled cake gently and discard parchment paper. Pipe alternating stripes of white chocolate whipped cream and whipped dark chocolate mousse vertically along the 12-inch side of the cake, in stripes about 1/2-inch thick. Continue this vertical pattern along the entirety of the cake, leaving about a 1/2-inch border on all sides. Gently roll the cake back up over the filling (without the parchment) as tightly as possible without breaking the cake until the cake is completely rolled up. Place onto a serving platter and refrigerate until set, at least 1 hour.", R.drawable.chocolaterollcake))
        foodlist.add(Food("Bread Pudding II", "My family LOVES bread pudding, and this recipe is one that I have fine tuned to their taste. I have to double this recipe, and bake it in a 9x13 inch pan for my family! It's great for breakfast or dessert and is delicious with milk poured on top! Enjoy!\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 45 mins\n" +
                "Total: 1 hr 15 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 8-inch square pan\n" +
                "\n" +
                "IGREDIENTS:\n" +
                "~ 6 slices day-old bread\n" +
                "~ 2 tablespoons butter, melted\n" +
                "~ ½ cup raisins (Optional)\n" +
                "~ 4 eggs, beaten\n" +
                "~ 2 cups milk\n" +
                "~ ¾ cup white sugar\n" +
                "~ 1 teaspoon ground cinnamon\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Break bread into small pieces into an 8 inch square baking pan. Drizzle melted butter or margarine over bread. If desired, sprinkle with raisins.\n" +
                "\n" +
                "Step 3\n" +
                "In a medium mixing bowl, combine eggs, milk, sugar, cinnamon, and vanilla. Beat until well mixed. Pour over bread, and lightly push down with a fork until bread is covered and soaking up the egg mixture.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven for 45 minutes, or until the top springs back when lightly tapped.", R.drawable.breadpuddingii))
        foodlist.add(Food("Coconut Rum", "This buttery, coconut-flavored rum cake puts a tasty twist on a rum cake. For better results, let sit overnight to let the flavors meld.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 45 mins\n" +
                "Additional: 30 mins\n" +
                "Total: 1 hr 35 mins\n" +
                "Servings: 16\n" +
                "Yield: 1 fluted tube cake\n" +
                "\n" +
                "INGREDIENTS\n" +
                "\n" +
                "CAKE:\n" +
                "~ ½ cup pecans, chopped\n" +
                "~ ½ cup sweetened flaked coconut, toasted\n" +
                "~ 1 (15.25 ounce) package butter cake mix\n" +
                "~ 1 (3.4 ounce) package instant coconut cream pudding mix\n" +
                "~ 4 large eggs\n" +
                "~ ½ cup coconut-flavored rum (such as Malibu®)\n" +
                "~ ½ cup coconut milk beverage (such as Silk®)\n" +
                "~ ½ cup coconut oil, melted\n" +
                "~ 1 teaspoon coconut extract\n" +
                "\n" +
                "GLAZE:\n" +
                "~ ½ cup butter\n" +
                "~ ½ cup coconut-flavored rum\n" +
                "~ ½ cup white sugar\n" +
                "~ ½ cup brown sugar\n" +
                "~ ¼ cup water\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 350 degrees F (175 degrees C). Spray a fluted tube pan (such as Bundt®) with cooking spray.\n" +
                "\n" +
                "Step 2\n" +
                "Stir pecans and coconut together. Spread evenly in the prepared Bundt® pan.\n" +
                "\n" +
                "Step 3\n" +
                "Combine cake mix, pudding mix, eggs, rum, coconut milk, coconut oil, and coconut extract in a bowl and beat with an electric mixer for 3 minutes, or until well incorporated, about 3 minutes.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in the preheated oven until a toothpick inserted into the center comes out clean, 45 to 55 minutes. Set on a wire rack until completely cooled, about 30 minutes. Run a table knife around the edges of pan to loosen cake. Invert carefully onto a serving plate.\n" +
                "\n" +
                "Step 5\n" +
                "While cake is cooling make glaze. Combine butter, rum, sugar, brown sugar and water in a saucepan over medium-low heat. Cook, stirring constantly, for 5 minutes.\n" +
                "\n" +
                "Step 6\n" +
                "Using a skewer, poke holes throughout cake. Carefully spoon glaze over cake", R.drawable.coconutrumcake))
        foodlist.add(Food("Golden Rum Cake", "My family requests this rummy Bundt cake from me at all our get-togethers. The butter rum glaze makes it special. An easy way to glaze your cake is to pour half of the glaze into Bundt pan, reinsert cake, then pour the rest of glaze over the bottom of the cake. Let absorb well then invert back onto platter.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 - 10 inch Bundt pan\n" +
                "\n" +
                "INGREDIETS\n" +
                "~ 1 cup chopped walnuts\n" +
                "~ 1 (18.25 ounce) package yellow cake mix\n" +
                "~ 1 (3.4 ounce) package instant vanilla pudding mix\n" +
                "~ 4 eggs\n" +
                "~ ½ cup water\n" +
                "~ ½ cup vegetable oil\n" +
                "~ ½ cup dark rum\n" +
                "~ ½ cup butter\n" +
                "~ ¼ cup water\n" +
                "~ 1 cup white sugar\n" +
                "~ ½ cup dark rum\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 325 degrees F (165 degrees C). Grease and flour a 10 inch Bundt pan. Sprinkle chopped nuts evenly over the bottom of the pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, combine cake mix and pudding mix. Mix in the eggs, 1/2 cup water, oil and 1/2 cup rum. Blend well. Pour batter over chopped nuts in the pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven for 60 minutes, or until a toothpick inserted into the cake comes out clean. Let sit for 10 minutes in the pan, then turn out onto serving plate. Brush glaze over top and sides. Allow cake to absorb glaze and repeat until all glaze is used.\n" +
                "\n" +
                "Step 4\n" +
                "To make the glaze: in a saucepan, combine butter, 1/4 cup water and 1 cup sugar. Bring to a boil over medium heat and continue to boil for 5 minutes, stirring constantly. Remove from heat and stir in 1/2 cup rum.", R.drawable.goldenrumcake))
        foodlist.add(Food("Fruit Pizza I", "A cookie dough crust, cream cheese filling, and fruit topping. Tip: For a quick crust, use one package of ready made sugar cookie dough rolled out to fit a pizza pan. Use an assortment of fresh fruit such as bananas, peaches, blueberries, kiwi, pineapple, and strawberries.\n" +
                "\n" +
                "Prep: 25 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 1 hr 35 mins\n" +
                "Servings: 10\n" +
                "Yield: 1 fruit pizza\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ½ cup butter, softened\n" +
                "~ ¾ cup white sugar\n" +
                "~ 1 egg\n" +
                "~ 1 ¼ cups all-purpose flour\n" +
                "~ 1 teaspoon cream of tartar\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ¼ teaspoon salt\n" +
                "~ 1 (8 ounce) package cream cheese\n" +
                "~ ½ cup white sugar\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, cream together the butter and 3/4 cup sugar until smooth. Mix in egg. combine the flour, cream of tartar, baking soda and salt; stir into the creamed mixture until just blended. Press dough into an ungreased pizza pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven for 8 to 10 minutes, or until lightly browned. Cool.\n" +
                "\n" +
                "Step 4\n" +
                "In a large bowl, beat cream cheese with 1/2 cup sugar and vanilla until light. Spread on cooled crust.\n" +
                "\n" +
                "Step 5\n" +
                "Arrange desired fruit on top of filling, and chill.", R.drawable.fruitpizzai))
        foodlist.add(Food("Tiramisu Layer Cake", "Fancy taste without all the work. This cake is wonderful for a get together or just a special occasion at home. Using a box cake mix as a base it's a real time saver!\n" +
                "\n" +
                "Prep: 5 mins\n" +
                "Cook: 20 mins\n" +
                "Additional: 1 hr 35 mins\n" +
                "Total: 2 hrs\n" +
                "Servings: 12\n" +
                "Yield: 1 - 3 layer 9 inch cake\n" +
                "\n" +
                "INGREDIENTS\n" +
                "\n" +
                "CAKE:\n" +
                "~ 1 (18.25 ounce) package moist white cake mix\n" +
                "~ 1 teaspoon instant coffee powder\n" +
                "~ ¼ cup coffee\n" +
                "~ 1 tablespoon coffee flavored liqueur\n" +
                "\n" +
                "FILLING:\n" +
                "~ 1 (8 ounce) container mascarpone cheese\n" +
                "~ ½ cup confectioners' sugar\n" +
                "~ 2 tablespoons coffee flavored liqueur\n" +
                "\n" +
                "FROSTING:\n" +
                "~ 2 cups heavy cream\n" +
                "~ ¼ cup confectioners' sugar\n" +
                "~ 2 tablespoons coffee flavored liqueur\n" +
                "\n" +
                "GARNISH:\n" +
                "~ 2 tablespoons unsweetened cocoa powder\n" +
                "~ 1 (1 ounce) square semisweet chocolate\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour 3 (9 inch) pans.\n" +
                "\n" +
                "Step 2\n" +
                "Prepare the cake mix according to package directions. Divide two thirds of batter between 2 pans. Stir instant coffee into remaining batter; pour into remaining pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven for 20 to 25 minutes, or until a toothpick inserted into the center of the cake comes out clean. Let cool in pan for 10 minutes, then turn out onto a wire rack and cool completely. In a measuring cup, combine brewed coffee and 1 tablespoon coffee liqueur; set aside.\n" +
                "\n" +
                "Step 4\n" +
                "To make the filling: In a small bowl, using an electric mixer set on low speed, combine mascarpone, 1/2 cup confectioners' sugar and 2 tablespoons coffee liqueur; beat just until smooth. Cover with plastic wrap and refrigerate.\n" +
                "\n" +
                "Step 5\n" +
                "To make the frosting: In a medium bowl, using an electric mixer set on medium-high speed, beat the cream, 1/4 cup confectioners' sugar and 2 tablespoons coffee liqueur until stiff. Fold 1/2 cup of cream mixture into filling mixture.\n" +
                "\n" +
                "Step 6\n" +
                "To assemble the cake: Place one plain cake layer on a serving plate. Using a thin skewer, poke holes in cake, about 1 inch apart. Pour one third of reserved coffee mixture over cake, then spread with half of the filling mixture. Top with coffee-flavored cake layer; poke holes in cake. Pour another third of the coffee mixture over the second layer and spread with the remaining filling. Top with remaining cake layer; poke holes in cake. Pour remaining coffee mixture on top. Spread sides and top of cake with frosting. Place cocoa in a sieve and lightly dust top of cake. Garnish with chocolate curls. Refrigerate at least 30 minutes before serving.\n" +
                "\n" +
                "Step 7\n" +
                "To make the chocolate curls, use a vegetable peeler and run it down the edge of the chocolate bar.", R.drawable.tiramisulayercake))
        foodlist.add(Food("Tiramisu Cheesecake", "Tiramisu-flavored cheesecake combines the flavors and richness of tiramisu and NY Cheesecake. Just before serving, grate some semisweet chocolate on the top.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 40 mins\n" +
                "Additional: 3 hrs 50 mins\n" +
                "Total: 5 hrs\n" +
                "Servings: 12\n" +
                "Yield: 1 8-inch cheesecake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (12 ounce) package ladyfingers\n" +
                "~ ¼ cup butter, melted\n" +
                "~ ¼ cup coffee-flavored liqueur, divided\n" +
                "~ 3 (8 ounce) packages cream cheese\n" +
                "~ 1 (8 ounce) container mascarpone cheese\n" +
                "~ 1 cup white sugar\n" +
                "~ 2 eggs\n" +
                "~ ¼ cup all-purpose flour\n" +
                "~ 1 (1 ounce) square semisweet chocolate\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Place a pan of water on the bottom of the oven.\n" +
                "\n" +
                "Step 2\n" +
                "Crush the package of ladyfingers to fine crumbs. Mix the melted butter into the crumbs. Moisten with 2 tablespoons of the coffee liqueur. Press into an 8-inch springform pan.\n" +
                "\n" +
                "Step 3\n" +
                "In a large bowl, mix cream cheese, mascarpone, and sugar until very smooth. Add 2 tablespoons coffee liqueur, and mix. Add the eggs and the flour; mix slowly just until smooth. Pour batter over crust in the springform pan.\n" +
                "\n" +
                "Step 4\n" +
                "Place pan on middle rack of oven. Bake until just set, 40 to 45 minutes. Open oven door, and turn off the heat. Leave cake to cool in oven for 20 minutes. Remove from oven, and let it finish cooling, about 30 minutes. Refrigerate for at least 3 hours, or overnight.\n" +
                "\n" +
                "Step 5\n" +
                "Grate semisweet chocolate over the top right before serving.", R.drawable.tiramisucheesecake))
        foodlist.add(Food("Pralines", "Had these in New Orleans and loved them, so I tried different combos and liked this best.\n" +
                "\n" +
                "Cook: 30 mins\n" +
                "Additional: 15 mins\n" +
                "Total: 45 mins\n" +
                "Servings: 20\n" +
                "Yield: 20 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 ½ cups toasted pecans\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ ⅜ cup butter\n" +
                "~ ¾ cup brown sugar\n" +
                "~ ½ cup milk\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Line a baking sheet with aluminum foil.\n" +
                "\n" +
                "Step 2\n" +
                "In large saucepan over medium heat, combine pecans, sugar, butter, brown sugar, milk and vanilla. Heat to between 234 and 240 degrees F (112 to 116 degrees C), or until a small amount of syrup dropped into cold water forms a soft ball that flattens when removed from the water and placed on a flat surface.\n" +
                "\n" +
                "Step 3\n" +
                "Drop by spoonfuls onto prepared baking sheet. Let cool completely.", R.drawable.pralines))
        foodlist.add(Food("Kanafa", "A favorite Palestinian dessert! Crunchy shredded phyllo dough is baked with a layer of creamy sweet cheese and then drenched in rosewater syrup. It's simple yet impressive. The cheese filling is traditionally made from Nabulsi cheese that is desalted. The ricotta-mozzarella mix is a great substitute, and lower-fat products can be used. If you have access to a Middle Eastern grocery, you can probably find kanafa dye, which turns the dough into the orange or reddish color that is the signature of kanafa.\n" +
                "\n" +
                "Prep: 25 mins\n" +
                "Cook: 35 mins\n" +
                "Total: 1 hr\n" +
                "Servings: 8\n" +
                "Yield: 1 9x13-inch pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (16 ounce) box shredded phyllo dough (kataifi)\n" +
                "~ 1 (15 ounce) container ricotta cheese\n" +
                "~ 1 cup shredded mozzarella cheese\n" +
                "~ ⅓ cup white sugar\n" +
                "~ 12 ounces unsalted butter\n" +
                "\n" +
                "FOR THE SYRUP:\n" +
                "~ 1 cup white sugar\n" +
                "~ ½ cup water\n" +
                "~ 1 teaspoon lemon juice\n" +
                "~ ⅛ teaspoon rose water (Optional)\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat an oven to 400 degrees F (200 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Use a food processor to finely chop the frozen, shredded phyllo dough. The strands should be about the size of a grain of rice. Pour the dough into a large mixing bowl. In a separate bowl, mix together the ricotta, mozzarella, and 1/3 cup sugar.\n" +
                "\n" +
                "Step 3\n" +
                "Place the butter in a large liquid measuring cup or bowl with a spout. Heat the butter in a microwave until completely melted, and let it sit for several minutes until a thick white foam has formed on top. Clarify the butter by using a spoon to skim off the foam.\n" +
                "\n" +
                "Step 4\n" +
                "Carefully pour the butter into the bowl of phyllo dough. Avoid pouring in the white milk solids at the bottom of the clarified butter. Use your hands to mix the butter and dough together. Make sure that the butter is absorbed by taking handfuls of the dough and rubbing it between your palms.\n" +
                "\n" +
                "Step 5\n" +
                "Evenly spread the buttered phyllo dough into a 9x13-inch pan and firmly press it into the bottom and edges. Spread the cheese mixture onto the dough, avoiding the edges of the pan.\n" +
                "\n" +
                "Step 6\n" +
                "Bake in the preheated oven until the cheese is slightly golden and the edges of dough are brown and bubbly, 30 to 35 minutes.\n" +
                "\n" +
                "Step 7\n" +
                "While the kanafa is baking, prepare the syrup. Combine the water and 1/2 cup sugar in a small saucepan. Bring the mixture to a boil over medium-high heat. Reduce the heat to medium and stir in the lemon juice. Simmer, stirring constantly, until the sugar is dissolved and the mixture is thickened, 5 to 7 minutes. (Do not let the mixture turn golden and caramelize.) Remove from heat and add the rose water; set aside.\n" +
                "\n" +
                "Step 8\n" +
                "Remove the kanafa from the oven. Place a large platter or baking sheet over the baking dish. Using oven mitts, carefully invert the baking dish onto the platter so the phyllo is on top. Pour the syrup over the kanafa. Cut into pieces and serve while hot.", R.drawable.kanafa))
        foodlist.add(Food("Twisty Cookies", "Scrumptious cookies with a tangy twist!!\n" +
                "\n" +
                "Servings: 30\n" +
                "Yield: 5 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 cup packed brown sugar\n" +
                "~ ½ cup butter, softened\n" +
                "~ 2 eggs\n" +
                "~ 2 cups all-purpose flour\n" +
                "~ 1 teaspoon baking soda\n" +
                "~ ½ teaspoon salt\n" +
                "~ 1 cup chopped almonds\n" +
                "~ 3 cups semi-sweet chocolate chips\n" +
                "~ 2 teaspoons rum\n" +
                "~ ½ teaspoon whiskey\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 375 degrees F (190 degrees C). Lightly grease cookie sheets.\n" +
                "\n" +
                "Step 2\n" +
                "Combine white and brown sugar, butter and eggs. Stir in flour, baking soda and salt. The dough will be stiff.\n" +
                "\n" +
                "Step 3\n" +
                "Stir in nuts, chocolate chips, rum and whiskey. Drop dough by tablespoonful onto cookie sheet. Bake 8 to 10 minutes.", R.drawable.twistycookies))
        foodlist.add(Food("Umm Ali", "Umm Ali is a delicious traditional Egyptian dessert equivalent to North America's bread pudding. You can serve it warm with a scoop of French vanilla ice cream.\n" +
                "\n" +
                "Prep: 10 mins\n" +
                "Cook: 30 mins\n" +
                "Total: 40 mins\n" +
                "Servings: 8\n" +
                "Yield: 8 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (17.25 ounce) package frozen puff pastry, thawed\n" +
                "~ 5 cups milk\n" +
                "~ 1 cup white sugar\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ ¼ cup raisins\n" +
                "~ ¼ cup slivered almonds\n" +
                "~ ¼ cup pine nuts\n" +
                "~ ¼ cup chopped pistachio nuts\n" +
                "~ ¼ cup sweetened, flaked coconut\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 400 degrees F (200 degrees C). Unroll the puff pastry sheets, and place flat on a baking sheet. Bake for 15 minutes in the preheated oven, or until puffed and golden brown.\n" +
                "\n" +
                "Step 2\n" +
                "Break the puff pastry into pieces, and place in a large bowl. Add the raisins, almonds, pine nuts, pistachios, and coconut, and toss to distribute. Pour into a 9x13 inch glass baking dish, and spread evenly.\n" +
                "\n" +
                "Step 3\n" +
                "Pour the milk into a saucepan, and stir in the sugar and vanilla. Heat until hot but not quite boiling. Pour over the mixture in the baking dish.\n" +
                "\n" +
                "Step 4\n" +
                "Bake for 15 minutes in the preheated oven. Turn the oven to broil, and broil for 2 minutes to brown the top. Remove from the oven and let stand for 5 minutes. Serve warm.", R.drawable.ummali))
        foodlist.add(Food("Southern Bread Pudding", "The simplicity of this bread pudding is what makes it so delicious. It's a classic custard dessert passed down through generations.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 20 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 9-inch pudding\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 4 cups cubed white sandwich bread\n" +
                "~ ¼ cup raisins\n" +
                "~ ⅛ teaspoon ground cinnamon, or to taste\n" +
                "~ ⅛ teaspoon freshly ground nutmeg, or to taste\n" +
                "~ 2 tablespoons melted butter\n" +
                "~ 2 cups half-and-half\n" +
                "~ 1 cup white sugar\n" +
                "~ 4 each eggs\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 pinch salt\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 400 degrees F (200 degrees C). Butter a 9-inch square baking dish.\n" +
                "\n" +
                "Step 2\n" +
                "Spread half of the bread in the bottom of the prepared baking dish. Top with raisins, cinnamon, nutmeg, and remaining bread. Sprinkle with more cinnamon and nutmeg and drizzle with melted butter.\n" +
                "\n" +
                "Step 3\n" +
                "Combine half-and-half, sugar, eggs, vanilla extract, and salt in a large bowl until well mixed and pour over bread. Press down on the bread with a fork to make sure it is saturated with the custard.\n" +
                "\n" +
                "Step 4\n" +
                "Place the baking dish in a large pan of boiling water and bake in the preheated oven until the center is set, about 1 hour.", R.drawable.southernbreadpudding))
        foodlist.add(Food("Orange Fluff I", "This is a light and fruity orange dessert salad that is also cool and creamy!\n" +
                "\n" +
                "Prep: 5 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 1 hr 5 mins\n" +
                "Servings: 8\n" +
                "Yield: 8 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 (11 ounce) cans mandarin oranges, drained\n" +
                "~ 1 (15 ounce) can crushed pineapple\n" +
                "~ 1 pound cottage cheese\n" +
                "~ 2 (3 ounce) packages orange flavored Jell-O mix\n" +
                "~ 1 (16 ounce) package frozen whipped topping, thawed\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Combine the well drained oranges and pineapple in a large bowl and mix together with the cottage cheese. Add the dry orange flavored gelatin powder and stir until well blended. Fold in the thawed frozen whipped topping and refrigerate for at least one hour.", R.drawable.orangefluffi))
        foodlist.add(Food("Kahlua Cake", "There isn't much to say except fa-bu-lous!\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 50 mins\n" +
                "Additional: 40 mins\n" +
                "Total: 1 hr 45 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 Bundt(R) cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ cooking spray\n" +
                "~ 1 (16.5 ounce) package devil's food cake mix\n" +
                "~ 1 (8 ounce) container sour cream\n" +
                "~ 4 eggs\n" +
                "~ ½ cup coffee-flavored liqueur (such as Kahlua®)\n" +
                "~ ½ cup vegetable oil\n" +
                "~ 1 (3.9 ounce) package instant chocolate pudding mix\n" +
                "~ 2 tablespoons grated orange zest\n" +
                "~ 1 teaspoon ground cinnamon\n" +
                "~ 1 (12 ounce) package mini chocolate chips\n" +
                "~ 1 tablespoon confectioners' sugar, or to taste\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease a fluted tube pan (such as Bundt) with cooking spray; dust with flour.\n" +
                "\n" +
                "Step 2\n" +
                "Combine cake mix, sour cream, eggs, coffee-flavored liqueur, oil, pudding mix, orange zest, and cinnamon in a large bowl; beat with an electric mixer on medium speed until very smooth, about 4 minutes. Fold in chocolate chips. Pour batter into the prepared tube pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven until a toothpick inserted into the center comes out clean, 50 to 60 minutes. Cool in the pan for 10 minutes. Invert onto a plate; let cool completely, about 30 minutes. Dust with confectioners' sugar.", R.drawable.chocolatekahluacake))
        foodlist.add(Food("Patriotic Fruit Pizza", "This is a fun recipe for the 4th of July holiday! Fresh fruit is a hit on desserts during the summer months! Making this dessert is as fun as eating it! This recipe feeds a lot, but the leftovers are only good a day or two.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 20 mins\n" +
                "Total: 1 hr\n" +
                "Servings: 24\n" +
                "Yield: 1 12x17 inch pizza\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 ¾ cups all-purpose flour\n" +
                "~ 1 teaspoon cream of tartar\n" +
                "~ 1 teaspoon baking soda\n" +
                "~ ¼ teaspoon salt\n" +
                "~ ½ cup vegetable shortening\n" +
                "~ ½ cup margarine, softened\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ 2 eggs\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 2 (8 ounce) packages cream cheese, softened\n" +
                "~ 1 cup white sugar\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 3 large bananas, sliced - or as needed\n" +
                "~ 1 tablespoon lemon juice, or as needed\n" +
                "~ 1 (16 ounce) package fresh strawberries, sliced\n" +
                "~ 1 (6 ounce) container fresh blueberries\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Whisk the flour, cream of tartar, baking soda, and salt in a bowl.\n" +
                "\n" +
                "Step 2\n" +
                "In a large mixing bowl, mash the vegetable shortening and margarine together until thoroughly combined, and beat in 1 1/2 cup of sugar, eggs, and 1 teaspoon of vanilla extract. Mix in the flour mixture to make a workable dough, and spread the dough out in a rectangle shape onto an ungreased 12x17 inch baking sheet.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven until very lightly browned, 8 to 10 minutes. Allow to cool completely.\n" +
                "\n" +
                "Step 4\n" +
                "While the cookie base is cooling, mash the cream cheese with 1 cup of sugar and 2 teaspoons of vanilla extract in a bowl until smooth. Place sliced bananas in a bowl, and gently toss with lemon juice to prevent browning.\n" +
                "\n" +
                "Step 5\n" +
                "To decorate the pizza, spread the cream cheese filling all over the cookie base in an even, smooth layer. Place the blueberries in a square in neat, closely-spaced rows, in the left upper corner for blue stars. Arrange alternating stripes of white bananas and red strawberry slices across the pizza. Refrigerate leftovers.", R.drawable.patrioticfruitpizza))
        foodlist.add(Food("Toffee Cookies ", "These cookies will become crisp after they have cooled.\n" +
                "\n" +
                "Servings: 18\n" +
                "Yield: 3 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ½ cup unsalted butter\n" +
                "~ 1 ⅛ cups white sugar\n" +
                "~ 1 egg\n" +
                "~ 1 tablespoon dark rum\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 cup all-purpose flour\n" +
                "~ ½ cup unsweetened cocoa powder\n" +
                "~ ½ teaspoon baking soda\n" +
                "~ ¼ teaspoon salt\n" +
                "~ 1 ½ cups toffee baking bits\n" +
                "~ ½ cup chopped almonds\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees F).\n" +
                "\n" +
                "Step 2\n" +
                "Beat butter and sugar in large bowl until fluffy. Add egg, rum, and vanilla and beat until well blended.\n" +
                "\n" +
                "Step 3\n" +
                "Sift flour, cocoa, baking soda and salt into small bowl. Stir dry ingredients into butter mixture. Mix in toffee and chopped almonds.\n" +
                "\n" +
                "Step 4\n" +
                "Drop batter by heaping tablespoonfuls onto heavy large ungreased baking sheet, spacing 2 inches apart. Bake until cookies puff slightly and crack on top but are still soft to tough, about 11 minutes. Let cookies cool on sheet 1 minutes. Transfer cookies to rack and cool completely. Repeat shaping and baking with remaining batter.", R.drawable.chocolatetoffeecookiesii))
        foodlist.add(Food("Cassata Cake", "Cassata cake is like a cannoli but more gooey. You need 2 - 9 inch sponge cake layers for this recipe. The instructions are given for to make them, or you can use your own recipe.\n" +
                "\n" +
                "Servings: 12\n" +
                "Yield: 1 -9 Inch cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 ½ cups cake flour\n" +
                "~ ½ teaspoon baking powder\n" +
                "~ ¼ teaspoon salt\n" +
                "~ 5 eggs\n" +
                "~ ½ cup cold water\n" +
                "~ 1 ¼ cups white sugar\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ ½ teaspoon cream of tartar\n" +
                "~ 2 pounds whole milk ricotta cheese\n" +
                "~ 2 ¼ cups confectioners' sugar\n" +
                "~ ½ teaspoon ground cinnamon\n" +
                "~ 1 ½ teaspoons vanilla extract\n" +
                "~ 2 (1 ounce) squares semi-sweet chocolate\n" +
                "~ ½ cup candied lemon peel\n" +
                "~ ⅓ cup white sugar\n" +
                "~ ¼ cup water\n" +
                "~ 2 tablespoons light rum\n" +
                "~ 6 (1 ounce) squares bittersweet chocolate, chopped\n" +
                "~ ⅓ cup heavy whipping cream\n" +
                "~ 3 tablespoons unsalted butter, cubed\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat the oven to 325 degrees F (165 degrees C). Grease and line with parchment paper 2 nine inch round layer pans.\n" +
                "\n" +
                "Step 2\n" +
                "Sift the flour, baking powder, and salt together.\n" +
                "\n" +
                "Step 3\n" +
                "Separate the eggs and set the egg whites aside. Beat the egg yolks together on medium-high speed until very thick, about 4 minutes. Gradually add the cold water. Add 1- 1/4 cups of the white sugar, slowly, and beat well for about 3 more minutes. Add 1 teaspoon of the vanilla. Sift the flour mixture over the egg yolk mixture and fold in.\n" +
                "\n" +
                "Step 4\n" +
                "Beat the egg whites and cream of tartar together until stiff peaks form. Fold this into the yolk mixture. Divide batter between the pans.\n" +
                "\n" +
                "Step 5\n" +
                "Bake at 325 degrees F (165 degrees C) for 25 minutes. Cool on rack for 10 minutes and then invert and cool completely.\n" +
                "\n" +
                "Step 6\n" +
                "Cut each cake layer in half. Place one of the 4 halves on a cake board or plate and sprinkle with a little of the Rum Syrup. Spread about 1-1/2 cups of the Filling over this layer. Add a second layer of cake and repeat this procedure. Top the cake with the last layer of cake. Chill at least 4 hours. Spread Chocolate Glaze over top of cake.\n" +
                "\n" +
                "Step 7\n" +
                "To Make Ricotta Cheese Filling: Beat the ricotta cheese well and add the confectioner's sugar and cinnamon. Add 1-1/2 teaspoons of the vanilla and grate 2 ounces of the chocolate in using the coarse side of a grater. Stir in the candied lemon peel and mix. Chill until ready to use.\n" +
                "\n" +
                "Step 8\n" +
                "To Make The Rum Syrup: Place 1/3 cup of the sugar and the water in a small saucepan. Bring to a boil over medium heat, stirring to dissolve sugar. Boil 1 minute and then remove from heat and add the rum. Cool to room temperature.\n" +
                "\n" +
                "Step 9\n" +
                "To Make The Chocolate Glaze: Melt 6 ounces of the chocolate and the cream in the microwave, whisk smooth. Add the butter and whisk until dissolved. Cool mixture until spreadable. Spread over the top of the cake.", R.drawable.cassatacake))
        foodlist.add(Food("Cream Cheesecake", "Prep: 20 mins\n" +
                "Cook: 1 hr 20 mins\n" +
                "Additional: 7 hrs 40 mins\n" +
                "Total: 9 hrs 20 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 9-inch Springform Pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 ½ cups chocolate cookie crumbs\n" +
                "~ ⅓ cup confectioners' sugar\n" +
                "~ ⅓ cup unsweetened cocoa powder\n" +
                "~ ¼ cup butter\n" +
                "~ 3 (8 ounce) packages cream cheese, softened\n" +
                "~ 1 ¼ cups white sugar\n" +
                "~ ¼ cup unsweetened cocoa powder\n" +
                "~ 3 tablespoons all-purpose flour\n" +
                "~ 3 eggs\n" +
                "~ ½ cup sour cream\n" +
                "~ ¼ cup Irish cream liqueur\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). In a large bowl, mix together the cookie crumbs, confectioners' sugar and 1/3 cup cocoa. Add melted butter and stir until well mixed. Pat into the bottom of a 9 inch springform pan. Bake in preheated oven for 10 minutes; set aside. Increase oven temperature to 450 degrees F (230 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, combine cream cheese, white sugar, 1/4 cup cocoa and flour. Beat at medium speed until well blended and smooth. Add eggs one at a time, mixing well after each addition. Blend in the sour cream and Irish cream liqueur; mixing on low speed. Pour filling over baked crust.\n" +
                "\n" +
                "Step 3\n" +
                "Bake at 450 degrees F (230 degrees C) for 10 minutes. Reduce oven temperature to 250 degrees F (120 degrees C), and continue baking for 60 minutes.\n" +
                "\n" +
                "Step 4\n" +
                "With a knife, loosen cake from rim of pan. Let cool, then remove the rim of pan. Chill before serving. If your cake cracks, a helpful tip is to dampen a spatula and smooth the top, then sprinkle with some chocolate wafer crumbs.", R.drawable.creamchocolatecheesecake))
        foodlist.add(Food("Chocolate Cavity", "Chocolate, chocolate, chocolate. This cake is so moist and rich there's absolutely no need for frosting. This cake made me an instant star with my clients. I quickly became known as 'that incredible chocolate cake lady!'\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Additional: 30 mins\n" +
                "Total: 2 hrs\n" +
                "Servings: 12\n" +
                "Yield: 1 - 10 inch Bundt pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (18.25 ounce) package dark chocolate cake mix\n" +
                "~ 1 (3.9 ounce) package instant chocolate pudding mix\n" +
                "~ 1 (16 ounce) container sour cream\n" +
                "~ 3 eggs\n" +
                "~ ⅓ cup vegetable oil\n" +
                "~ ½ cup coffee flavored liqueur\n" +
                "~ 2 cups semisweet chocolate chips\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour a 10 inch Bundt pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, combine cake mix, pudding mix, sour cream, eggs, oil and coffee liqueur. Beat until ingredients are well blended. Fold in chocolate chips. Batter will be thick. Spoon into prepared pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven for 1 hour, or until cake springs back when lightly tapped. Cool 10 minutes in pan, then turn out and cool completely on wire rack.", R.drawable.chocolatecavity))
        foodlist.add(Food("Marble Cake", "This is a lovely cake with the taste of almond and chocolate and it is almost like a pound cake.\n" +
                "\n" +
                "Servings: 14\n" +
                "Yield: 1 -10 inch tube cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup butter\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ 4 eggs\n" +
                "~ 1 cup milk\n" +
                "~ 1 teaspoon almond extract\n" +
                "~ 3 ¼ cups all-purpose flour\n" +
                "~ 1 tablespoon baking powder\n" +
                "~ ⅛ teaspoon salt\n" +
                "~ ¼ cup unsweetened cocoa powder\n" +
                "~ 3 tablespoons dark rum\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour one 10 inch tube pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, cream the butter with the sugar. Beat in the eggs, then the milk and almond extract.\n" +
                "\n" +
                "Step 3\n" +
                "In another bowl, stir together the flour, baking powder and salt. Beat the flour mixture into the creamed mixture. Turn half of the batter into another bowl and stir in the cocoa and rum.\n" +
                "\n" +
                "Step 4\n" +
                "Layer the light and dark batters by large spoonfuls and then swirl slightly with a knife.\n" +
                "\n" +
                "Step 5\n" +
                "Bake the cake in at 350 degree F (175 degree C) for about 70 minutes, or until it tests done with a toothpick. Transfer to a rack to cool. Makes about 14 to 16 servings.", R.drawable.marblecake))
        foodlist.add(Food("Simple Fruit Pizza", "This is a great dessert that is easy and delicious. You can use whatever fruits you like.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 1 hr 30 mins\n" +
                "Total: 2 hrs\n" +
                "Servings: 10\n" +
                "Yield: 1 12-inch pizza\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (18 ounce) package refrigerated sugar cookie dough\n" +
                "~ 1 (8 ounce) package cream cheese, softened\n" +
                "~ ⅓ cup white sugar\n" +
                "~ ½ teaspoon vanilla extract\n" +
                "~ 1 pint fresh strawberries, sliced\n" +
                "~ 1 pint fresh blueberries\n" +
                "~ 2 bananas, sliced\n" +
                "~ 2 kiwis, peeled and sliced\n" +
                "~ ½ cup orange marmalade\n" +
                "~ 2 tablespoons water\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 375 degrees F (190 degrees C). Lightly grease a 12-inch pizza pan.\n" +
                "\n" +
                "Step 2\n" +
                "Press cookie dough into pizza pan; prick holes in dough with a fork.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven until golden, 10 to 15 minutes. Allow cookie crust to cool.\n" +
                "\n" +
                "Step 4\n" +
                "Beat cream cheese, sugar, and vanilla extract until smooth; spread over cooled crust. Arrange strawberries, blueberries, bananas, and kiwi decoratively over cream cheese mixture.\n" +
                "\n" +
                "Step 5\n" +
                "Mix orange marmalade and water in a small bowl; spoon mixture over fruit. Chill for 1 hour before serving.", R.drawable.simplefruitpizza))
        foodlist.add(Food("Cocoa Rum Balls", "This delicious smooth confection is an impressive dessert for the holidays. These are wonderful for parties, and perfect for holiday gifts to your loved ones. A half cup of orange juice plus one teaspoon of freshly grated orange peel can be substituted for the rum in this recipe.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Additional: 2 days\n" +
                "Total: 2 days\n" +
                "Servings: 24\n" +
                "Yield: 4 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (12 ounce) package vanilla wafers, crushed\n" +
                "~ 1 ½ cups chopped nuts\n" +
                "~ ¾ cup confectioners' sugar\n" +
                "~ ¼ cup cocoa\n" +
                "~ ½ cup light rum\n" +
                "~ 3 tablespoons light corn syrup\n" +
                "~ ⅛ cup confectioners' sugar\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a large bowl, combine vanilla wafer crumbs, chopped nuts, 3/4 cup confectioners' sugar, and cocoa. Mix in rum and corn syrup. Shape dough into 1 inch balls; roll in confectioners' sugar.\n" +
                "\n" +
                "Step 2\n" +
                "Store rum balls in an airtight container for 2 to 3 days to develop flavor. Roll them again in confectioners' sugar before serving.", R.drawable.cocoarumballs))
        foodlist.add(Food("Black Forest Cake", "This recipe is from a friend of my mother's. She used it for catering and has won the hearts of most who've tasted it. I've had to share this recipe with everyone who's been to one of my parties. Serve with whipped cream if desired.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 30 mins\n" +
                "Total: 1 hr\n" +
                "Servings: 10\n" +
                "Yield: 1 - 2 layer 8 inch cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup milk\n" +
                "~ 1 tablespoon vinegar\n" +
                "~ 1 ¾ cups all-purpose flour\n" +
                "~ 2 cups white sugar\n" +
                "~ ¾ cup unsweetened cocoa powder\n" +
                "~ 1 teaspoon baking powder\n" +
                "~ 2 teaspoons baking soda\n" +
                "~ ½ teaspoon salt\n" +
                "~ 2 eggs\n" +
                "~ ½ cup vegetable oil\n" +
                "~ 1 cup strong brewed coffee, cold\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 (21 ounce) can cherry pie filling\n" +
                "~ ½ cup cherry liqueur\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Grease and flour two 8 inch cake pans. Make sour milk by combining milk and vinegar. Set aside.\n" +
                "\n" +
                "Step 2\n" +
                "Sift together the flour, sugar, cocoa powder, baking soda, baking powder and salt. Set aside. In a large bowl, whisk together the eggs, oil, coffee and vanilla. Stir in the sour milk. Gradually beat in the flour mixture, mixing just until incorporated.\n" +
                "\n" +
                "Step 3\n" +
                "Pour batter into prepared pans. Bake in the preheated oven for 30 minutes, or until a toothpick inserted into the center of the cake comes out clean. Allow cake layers to cool completely before filling.\n" +
                "\n" +
                "Step 4\n" +
                "To make the cherry filling: Combine the cherry pie filling and cherry liquor. Refrigerate cherry mixture until chilled, then fill cake.", R.drawable.blackforestcake))
        foodlist.add(Food("Pastia", "Pastia is what Italians make at Easter.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 1 hr 15 mins\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 20\n" +
                "Yield: 1 9x13 inch baking dish\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ ½ pound thin egg noodles, cooked\n" +
                "~ ½ cup butter\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ 1 quart milk\n" +
                "~ 1 pint heavy cream\n" +
                "~ 1 ounce anise extract\n" +
                "~ 3 tablespoons vanilla extract\n" +
                "~ 12 eggs\n" +
                "~ 8 ounces ricotta cheese\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 300 degrees F (150 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "In large mixing bowl, combine noodles, butter, sugar, milk, cream, anise, vanilla, eggs and ricotta. Spoon into 9x13 inch baking dish and bake for 1 1/4 hours, until golden and knife inserted in center comes out clean. Cool and serve.", R.drawable.pastia))
        foodlist.add(Food("Brownie Trifle", "Chocolate chunk brownies layered between a mixture of chocolate pudding, cream cheese, and whipped topping.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 20 mins\n" +
                "Additional: 1 hr 30 mins\n" +
                "Total: 2 hrs 20 mins\n" +
                "Servings: 12\n" +
                "Yield: 12 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (21.5 ounce) package chocolate chunk brownie mix (such as Duncan Hines®)\n" +
                "~ 2 eggs\n" +
                "~ ¼ cup water\n" +
                "~ ¼ cup vegetable oil\n" +
                "~ 1 (8 ounce) package cream cheese, softened\n" +
                "~ 3 cups cold milk\n" +
                "~ 1 (3.9 ounce) package instant chocolate pudding mix\n" +
                "~ 1 (8 ounce) container frozen whipped topping, thawed\n" +
                "~ 6 ounces chocolate almond bark\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 325 degrees F (165 degrees C). Grease a 9x13-inch baking pan.\n" +
                "\n" +
                "Step 2\n" +
                "Combine brownie mix, eggs, water, and oil in a large bowl; stir until well blended. Spread mixture evenly into prepared pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven until top is dry and edges have started to pull away from the sides of the pan, 20 to 25 minutes. Cool completely, about 30 minutes. Cut into 1-inch squares.\n" +
                "\n" +
                "Step 4\n" +
                "Beat cream cheese in a large bowl with an electric mixer. Add milk and pudding mix; beat until smooth. Fold in whipped topping until well incorporated into filling.\n" +
                "\n" +
                "Step 5\n" +
                "Place 1/2 of the brownie squares at the bottom of a trifle bowl or glass serving dish; top with filling. Shave 1/2 of the chocolate bar onto filling using a vegetable peeler. Repeat with remaining brownies, filling, and chocolate bar shavings. Refrigerate until set, about 1 hour.", R.drawable.chocolatebrownietrifle))
        foodlist.add(Food("Whiskey Cake", "Although most of the alcohol cooks out of this cake, there's still a distinct whiskey flavor.\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Cook: 1 hr 10 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 2 hrs 30 mins\n" +
                "Servings: 12\n" +
                "Yield: 1 10-inch Bundt cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup chopped walnuts\n" +
                "~ 1 (18.25 ounce) package yellow cake mix\n" +
                "~ 1 (5 ounce) package instant French vanilla pudding mix\n" +
                "~ ½ cup cold water\n" +
                "~ ½ cup vegetable oil\n" +
                "~ 4 eggs\n" +
                "~ ½ cup whiskey\n" +
                "~ ½ cup butter\n" +
                "~ ¼ cup water\n" +
                "~ 1 cup white sugar\n" +
                "~ ½ cup whiskey\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 325 degrees F (165 degrees C). Grease and flour a 10-inch Bundt pan or tube cake pan. Sprinkle the walnuts evenly in the bottom of the prepared pan.\n" +
                "\n" +
                "Step 2\n" +
                "Place the cake mix, pudding mix, 1/2 cup water, vegetable oil, eggs, and 1/2 cup whiskey into a mixing bowl, and beat until well blended with an electric mixer, about 2 minutes. Pour the batter over the nuts in the pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven until the cake is set and the top springs back when pushed slightly, about 1 hour. When the cake is completely cool, run a paring knife between the cake and the edge of the pan. Hold the cake pan on its side and gently tap the sides of the pan against the counter to loosen it. Cover the cake pan with a plate or cooling rack, and invert it to tip the cake out of the pan and onto the plate. Poke holes all over the top of the cake with a toothpick.\n" +
                "\n" +
                "Step 4\n" +
                "To make glaze, melt butter in a saucepan over low heat, then pour in 1/4 cup water and the sugar. Stir the mixture together until smooth, and bring to a boil over medium-low heat; boil for 5 minutes, then remove from heat and stir in 1/2 cup whiskey. Let the mixture cool about 10 minutes, then pour the warm glaze over the cake, allowing it to soak into the holes in the cake.", R.drawable.whiskeycake))
        foodlist.add(Food("Sans Rival", "Sans Rival a very yummy layered dessert. Each layer is topped with a nice amount of icing like filling and cashews on top. This recipe is definitely for cashew lovers out there!\n" +
                "\n" +
                "Prep: 2 hrs\n" +
                "Cook: 45 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 3 hrs 45 mins\n" +
                "Servings: 24\n" +
                "Yield: 24 servings\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 12 egg whites\n" +
                "~ 2 cups white sugar\n" +
                "~ 1 teaspoon cream of tartar\n" +
                "~ 2 cups chopped cashews, toasted\n" +
                "~ 1 pound butter, softened\n" +
                "~ 12 egg yolks\n" +
                "~ 2 cups white sugar\n" +
                "~ 1 cup water\n" +
                "~ 2 tablespoons dark rum\n" +
                "~ 2 cups chopped cashews, toasted\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 300 degrees F (150 degrees C). Line 4 - 8x12 inch pans with parchment paper.\n" +
                "\n" +
                "Step 2\n" +
                "In a large glass or metal mixing bowl, beat egg whites until foamy. Sprinkle with cream of tartar. Gradually add 2 cups white sugar, continuing to beat until stiff peaks form. Fold in 2 cups chopped nuts. Divide meringue between the 4 pans, and spread evenly to edges.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in preheated oven for 30 minutes, or until light golden brown. Remove the meringue from the baking pans while still hot; allow to cool. When cool, trim edges so that all 4 meringue layers are uniformly shaped. Set aside.\n" +
                "\n" +
                "Step 4\n" +
                "To make buttercream filling: In a large bowl, Beat butter until light and fluffy. In a separate large bowl, whip egg yolks until thick and lemon colored.\n" +
                "\n" +
                "Step 5\n" +
                "Meanwhile, in a saucepan, combine 2 cups sugar and 1 cup water. Bring to a boil, and heat to 270 to 290 degrees F (132 to 143 degrees C), or until a small amount of syrup dropped into cold water forms hard but pliable threads. Add the hot syrup in a thin stream to the beaten egg yolks, while beating at high speed. Beat in the whipped butter until smooth. Beat in rum.\n" +
                "\n" +
                "Step 6\n" +
                "On one layer of meringue, spread approximately 1/4 of the buttercream evenly to edges. Repeat layers until all is used. Smooth sides with spatula. Sprinkle top with 2 cups chopped cashews. Freeze until solid. To serve, cut 1 inch slices from the frozen dessert.", R.drawable.sansrival))
        foodlist.add(Food("Rum Truffles", "These truffles will be soft inside despite that it feels firm on the outside. You only need to store them in a cool place, but not necessarily the fridge. Make sure you always use an airtight container! Be sure to use good quality chocolate!\n" +
                "\n" +
                "Prep: 10 mins\n" +
                "Cook: 10 mins\n" +
                "Additional: 1 hr 30 mins\n" +
                "Total: 1 hr 50 mins\n" +
                "Servings: 24\n" +
                "Yield: 24 truffles\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 8 (1 ounce) squares bittersweet chocolate, chopped\n" +
                "~ ¼ cup cream\n" +
                "~ 2 tablespoons unsalted butter\n" +
                "~ ½ cup chocolate cake crumbs\n" +
                "~ 2 teaspoons dark rum\n" +
                "~ ½ cup chocolate sprinkles\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Line a sheet pan with aluminum foil or parchment paper. Place chopped chocolate in a heatproof bowl.\n" +
                "\n" +
                "Step 2\n" +
                "In a saucepan, combine cream and butter. Place over low heat, and bring to a boil. Pour over chocolate, and stir until chocolate is melted and smooth. Stir in cake crumbs and rum. Set aside until firm, but not hard.\n" +
                "\n" +
                "Step 3\n" +
                "Roll heaping teaspoons of chocolate mixture into balls, then roll in the chocolate sprinkles. Place on the prepared tray. Refrigerate 30 minutes or until firm. Serve in small paper cups.", R.drawable.rumtruffles))
        foodlist.add(Food("Kentucky Derby", "This is a pie traditionally served at the annual Kentucky Derby for 50 years.\n" +
                "\n" +
                "Prep: 15 mins\n" +
                "Cook: 1 hr 50 mins\n" +
                "Total: 2 hrs 5 mins\n" +
                "Servings: 10\n" +
                "Yield: 1 9-inch pie\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 ¼ cups chopped pecans\n" +
                "~ 4 large eggs\n" +
                "~ ¾ cup brown sugar\n" +
                "~ ¾ cup light corn syrup\n" +
                "~ ½ cup all-purpose flour\n" +
                "~ ½ cup butter, melted and cooled\n" +
                "~ ¼ cup white sugar\n" +
                "~ 2 tablespoons bourbon\n" +
                "~ 1 ½ teaspoons vanilla extract\n" +
                "~ ¾ cup miniature semisweet chocolate chips\n" +
                "~ 1 (9 inch) unbaked deep dish pie crust\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 300 degrees F (150 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Spread pecans over a cookie sheet.\n" +
                "\n" +
                "Step 3\n" +
                "Bake pecans in preheated oven until toasted, about 1 hour, stirring every 15 minutes. Check pecans after 30 minutes. Allow pecans to cool completely.\n" +
                "\n" +
                "Step 4\n" +
                "Increase oven temperature to 350 degrees F (175 degrees C).\n" +
                "\n" +
                "Step 5\n" +
                "Whisk eggs, brown sugar, light corn syrup, flour, butter, white sugar, bourbon, and vanilla extract together in a bowl until smooth. Fold pecan pieces and chocolate chips into the egg mixture until combined; pour into prepared pie crust.\n" +
                "\n" +
                "Step 6\n" +
                "Bake in preheated oven until pie is set, 50 to 60 minutes. Serve warm or chilled.", R.drawable.kentuckyderbydessert))
        foodlist.add(Food("Chow Clusters", "Simple and delicious ...more like candy. Peanuts work well in place of the cashews or try a combination of both!\n" +
                "\n" +
                "Servings: 18\n" +
                "Yield: 3 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 cups semisweet chocolate chips\n" +
                "~ 2 cups butterscotch chips\n" +
                "~ 2 (5 ounce) cans chow mein noodles\n" +
                "~ ½ cup cashew halves\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a heavy saucepan, combine chocolate and butterscotch chips. Melt, stirring constantly over low heat.\n" +
                "\n" +
                "Step 2\n" +
                "Remove when melted and add chow mein noodles. Add cashews or peanuts. Mix quickly to coat Dip out tablespoons onto wax paper. Cool. (Can also melt chips in the microwave.)", R.drawable.chowclusters))
        foodlist.add(Food("Fruitcake", "This is my 90-year-old grandmother's recipe. Very simple.\n" +
                "\n" +
                "Servings: 16\n" +
                "Yield: 1 - 9 inch tube cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 2 cups packed brown sugar\n" +
                "~ 3 cups all-purpose flour\n" +
                "~ 1 tablespoon baking soda\n" +
                "~ 2 tablespoons ground cinnamon\n" +
                "~ 2 tablespoons ground cloves\n" +
                "~ 2 tablespoons ground allspice\n" +
                "~ 2 tablespoons ground nutmeg\n" +
                "~ 4 eggs\n" +
                "~ 2 tablespoons lemon zest\n" +
                "~ 2 tablespoons vanilla extract\n" +
                "~ ½ cup brandy\n" +
                "~ 1 ½ cups raisins\n" +
                "~ 1 ½ cups chopped nuts\n" +
                "~ 1 ½ cups dried mixed fruit\n" +
                "~ 1 ½ cups butter, melted\n" +
                "~ 1 ¾ cups brandy\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 225 degrees F (110 degrees C). Grease and flour a tube pan.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, combine sugar, flour, soda, spices, eggs, lemon rind, vanilla, 1/2 cup brandy, fruit, nuts, and melted butter or margarine. Mix thoroughly. Pour into prepared pan.\n" +
                "\n" +
                "Step 3\n" +
                "Bake for 1 hour, or until a tester inserted in the center comes out clean. Cool on a wire rack.\n" +
                "\n" +
                "Step 4\n" +
                "Wrap cooled cake in foil. Sprinkle 2 tablespoons brandy over the cake everyday for 2 weeks.", R.drawable.fruitcake))
        foodlist.add(Food("Texas Pralines", "Chewy Texas pralines we make every year for Christmas! Delicious!\n" +
                "\n" +
                "Prep: 10 mins\n" +
                "Cook: 20 mins\n" +
                "Total: 30 mins\n" +
                "Servings: 56\n" +
                "Yield: 56 pralines\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ nonstick cooking spray\n" +
                "~ 2 cups white sugar\n" +
                "~ 2 cups light corn syrup\n" +
                "~ 1 pound butter\n" +
                "~ 2 cups heavy cream\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 8 cups pecans\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Line 2 baking sheets with aluminum foil. Coat with nonstick cooking spray.\n" +
                "\n" +
                "Step 2\n" +
                "In a large saucepan over medium heat, combine sugar and corn syrup. Heat to 250 degrees F (120 degrees C). Remove from heat, and stir in butter until melted. Gradually stir in cream. Return to heat. Cook, stirring constantly, until temperature reaches 242 degrees F (116 degrees C). Remove from heat, and stir in vanilla and pecans.\n" +
                "\n" +
                "Step 3\n" +
                "Drop by spoonful onto prepared pans. Cool completely, then wrap with plastic.", R.drawable.texaspralines))
        foodlist.add(Food("Very Berry", "A rich reward for picking your way through the thorny brambles. Use your choice of fresh raspberries, blueberries, blackberries, boysenberries and/or chopped pitted cherries. If you don't want to use alcohol, you may substitute orange juice.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 1 hr\n" +
                "Total: 1 hr 30 mins\n" +
                "Servings: 10\n" +
                "Yield: 1 - 8 inch springform pan\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 40 vanilla wafers, crushed\n" +
                "~ 6 tablespoons butter, melted\n" +
                "~ 2 (8 ounce) packages cream cheese, softened\n" +
                "~ ¾ cup white sugar\n" +
                "~ 2 tablespoons all-purpose flour\n" +
                "~ 2 teaspoons vanilla extract\n" +
                "~ 1 cup cottage cheese, creamed\n" +
                "~ ¼ cup cherry brandy\n" +
                "~ 3 eggs\n" +
                "~ 3 ½ cups fresh blackberries\n" +
                "~ 1 tablespoon cherry brandy\n" +
                "~ 1 tablespoon white sugar\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a medium bowl, stir together vanilla wafer crumbs and butter. Press the mixture into the bottom and 1 3/4 inch up the sides of an 8 inch springform pan. Set aside.\n" +
                "\n" +
                "Step 2\n" +
                "In a large bowl, stir together cream cheese, 3/4 cup sugar, flour, and vanilla. Beat with an electric mixer on low speed until smooth. Set aside. Place cottage cheese in a blender or food processor. Blend until smooth. Stir into cream cheese mixture. Stir in the 1/4 cup cherry brandy. Beat in eggs on low speed just till combined. You do not want do incorporate too much air into the batter.\n" +
                "\n" +
                "Step 3\n" +
                "Preheat the oven to 375 degrees F (190 degrees C). Pour half of the cheese mixture into the crust-lined pan. Spread 1 cup of the fruit on top. Top with remaining cheese mixture and 1/2 cup of the fruit. Place in a shallow baking pan in the preheated oven.\n" +
                "\n" +
                "Step 4\n" +
                "Bake for 40 to 45 minutes or till center appears nearly set when shaken. Cool on a wire rack for 15 minutes. Loosen sides. Cool completely on wire rack. Cover and chill for at least 4 hours, or until ready to serve.\n" +
                "\n" +
                "Step 5\n" +
                "For topping, in a medium bowl combine remaining 2 cups fruit, 1 tablespoon cherry brandy, and 1 tablespoon sugar. Cover and chill for up to 2 hours. To serve, cut cheesecake into wedges. Top each serving with fruit topping.", R.drawable.veryberrycheesecake))
        foodlist.add(Food("Black Forest II", "Wonderful chocolate layer cake which is soaked in Kirsch liqueur, with cherry filling.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Cook: 40 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 2 hrs 10 mins\n" +
                "Servings: 12\n" +
                "Yield: 2 layer 8 inch round cake\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 ⅔ cups all-purpose flour\n" +
                "~ ⅔ cup unsweetened cocoa powder\n" +
                "~ 1 ½ teaspoons baking soda\n" +
                "~ 1 teaspoon salt\n" +
                "~ ½ cup shortening\n" +
                "~ 1 ½ cups white sugar\n" +
                "~ 2 eggs\n" +
                "~ 1 teaspoon vanilla extract\n" +
                "~ 1 ½ cups buttermilk\n" +
                "~ ½ cup kirschwasser\n" +
                "~ ½ cup butter\n" +
                "~ 3 ½ cups confectioners' sugar\n" +
                "~ 1 pinch salt\n" +
                "~ 1 teaspoon strong brewed coffee\n" +
                "~ 2 (14 ounce) cans pitted Bing cherries, drained\n" +
                "~ 2 cups heavy whipping cream\n" +
                "~ ½ teaspoon vanilla extract\n" +
                "~ 1 tablespoon kirschwasser\n" +
                "~ 1 (1 ounce) square semisweet chocolate\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat oven to 350 degrees F (175 degrees C). Line the bottoms of two 8 inch round pans with parchment paper circles. Sift together flour, cocoa, baking soda and 1 teaspoon salt. Set aside.\n" +
                "\n" +
                "Step 2\n" +
                "Cream shortening and sugar until light and fluffy. Beat in eggs and vanilla. Beat in flour mixture, alternating with buttermilk, until combined. Pour into 2 round 8 inch pans.\n" +
                "\n" +
                "Step 3\n" +
                "Bake at 350 degrees F (175 degrees C) for 35 to 40 minutes, or until a toothpick inserted into the cake comes out clean. Cool completely. Remove paper from the cakes. Cut each layer in half, horizontally, making 4 layers total. Sprinkle layers with the 1/2 cup kirshwasser.\n" +
                "\n" +
                "Step 4\n" +
                "In a medium bowl, cream the butter until light and fluffy. Add confectioners sugar, pinch of salt, and coffee; beat until smooth. If the consistency is too thick, add a couple teaspoons of cherry juice or milk. Spread first layer of cake with 1/3 of the filling. Top with 1/3 of the cherries. Repeat with the remaining layers.\n" +
                "\n" +
                "Step 5\n" +
                "In a separate bowl, whip the cream to stiff peaks. Beat in 1/2 teaspoon vanilla and 1 tablespoon kirshwasser. Frost top and sides of cake. Sprinkle with chocolate curls made by using a potato peeler on semisweet baking chocolate.", R.drawable.blackforestcakeii))
        foodlist.add(Food("Margarita Balls", "Right up there with Rum Balls.\n" +
                "\n" +
                "Prep: 30 mins\n" +
                "Total: 30 mins\n" +
                "Servings: 24\n" +
                "Yield: 4 dozen\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 (12 ounce) package vanilla wafers\n" +
                "~ ½ pound ground almonds\n" +
                "~ 4 ounces white chocolate\n" +
                "~ ¼ cup tequila\n" +
                "~ ¼ cup orange marmalade\n" +
                "~ 2 tablespoons light corn syrup\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Mix 1 box (12 oz) vanilla wafers, crushed into crumbs, with 1/2 lb ground blanched almonds.\n" +
                "\n" +
                "Step 2\n" +
                "Melt four 1 oz squares white chocolate according to package directions.\n" +
                "\n" +
                "Step 3\n" +
                "In blender, process tequila, orange marmalade, and light corn syrup until smooth. Stir, along with melted chocolate, into crumb mixture.\n" +
                "\n" +
                "Step 4\n" +
                "Shape into 1-inch balls and coat with sugar. Store in refrigerator.", R.drawable.margaritaballi))
        foodlist.add(Food("Bourbon Pecan Pie", "This is a pecan pie you won't want to forget. Bourbon gives this pie its delicious and distinctive flavor.\n" +
                "\n" +
                "Prep:15 mins\n" +
                "Cook:35 mins\n" +
                "Additional:1 hr\n" +
                "Total:1 hr 50 mins\n" +
                "Servings:8\n" +
                "Yield:1 9-inch pie\n" +
                "\n" +
                "INGREDIENTS;\n" +
                "~ ½ cup white sugar\n" +
                "~ ½ cup brown sugar\n" +
                "~ 3 tablespoons butter, melted\n" +
                "~ ½ cup light corn syrup\n" +
                "~ 3 eggs, beaten\n" +
                "~ 2 tablespoons bourbon\n" +
                "~ 2 cups pecan halves\n" +
                "~ 1 (9 inch) unbaked deep-dish pie crust\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "Preheat an oven to 375 degrees F (190 degrees C).\n" +
                "\n" +
                "Step 2\n" +
                "Mix the white sugar, brown sugar, and butter together in a bowl. Stir in the corn syrup, eggs, and bourbon; fold in the pecans. Pour the mixture into the pie crust.\n" +
                "\n" +
                "Step 3\n" +
                "Bake in the preheated oven for 10 minutes; reduce heat to 350 degrees F (175 degrees C); continue to bake until the pie is set, about 25 minutes more. Allow to cool completely on a wire rack before serving.", R.drawable.bourbonpecanpie))
        foodlist.add(Food("Raspberry Tart", "Fresh summer raspberries in raspberry jam on a buttery crust.\n" +
                "\n" +
                "Prep: 1 hr 10 mins\n" +
                "Additional: 1 hr\n" +
                "Total: 2 hrs 10 mins\n" +
                "Servings: 8\n" +
                "Yield: 1 - 9 inch tart\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 cup all-purpose flour\n" +
                "~ ½ cup butter\n" +
                "~ 2 tablespoons confectioners' sugar\n" +
                "~ 4 cups fresh raspberries\n" +
                "~ 1 (8 ounce) jar raspberry jam\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a medium bowl, blend together the flour, butter and sugar. Chill mixture for 1 hour.\n" +
                "\n" +
                "Step 2\n" +
                "Preheat oven to 375 degrees F (190 degrees C).\n" +
                "\n" +
                "Step 3\n" +
                "Pat chilled mixture into a 9 inch tart pan.\n" +
                "\n" +
                "Step 4\n" +
                "Bake in preheated oven for 10 minutes. Once out of the oven, allow to cool.\n" +
                "\n" +
                "Step 5\n" +
                "Arrange raspberries in crust. Heat jar of jam in microwave until it begins to boil. Pour jam over fruit. Cover and refrigerate tart for about 1 hour.", R.drawable.raspberrytart))
        foodlist.add(Food("Raspberry Trifle", "Layered dessert that should, in theory, feed a lot of people! I know it's not a typical cake, but I think it's better than that! And it really does feed a lot of people!\n" +
                "\n" +
                "Prep: 20 mins\n" +
                "Additional: 4 hrs\n" +
                "Total: 4 hrs 20 mins\n" +
                "Servings: 18\n" +
                "Yield: 1 trifle\n" +
                "\n" +
                "INGREDIENTS:\n" +
                "~ 1 ½ cups heavy cream\n" +
                "~ ¼ cup white sugar\n" +
                "~ 2 (8 ounce) packages cream cheese, softened\n" +
                "~ 2 teaspoons lemon juice\n" +
                "~ 1 ½ teaspoons vanilla extract\n" +
                "~ ½ cup white sugar\n" +
                "~ 1 (10.75 ounce) package prepared pound cake\n" +
                "~ 2 (10 ounce) packages frozen raspberries, thawed\n" +
                "~ 2 tablespoons unsweetened cocoa powder, for dusting\n" +
                "\n" +
                "DIRECTIONS:\n" +
                "\n" +
                "Step 1\n" +
                "In a medium bowl, beat cream with 1/4 cup sugar until stiff peaks form. In another bowl, cream together cream cheese, lemon juice, vanilla and 1/2 cup sugar. Fold 2 cups of whipped cream into cream cheese mixture. Reserve remaining whipped cream.\n" +
                "\n" +
                "Step 2\n" +
                "Slice pound cake into 18 - 1/2 inch slices. Drain raspberries, reserving juice. Line the bottom of a 3 quart glass bowl or trifle bowl with one-third of the cake slices. Drizzle with some raspberry juice. Spread one-fourth of the cream cheese mixture over cake. Sift one-fourth of the cocoa over that. Sprinkle with one-third of the raspberries. Repeat layers twice. Top with remaining cream cheese mixture, whipped cream and sifted cocoa. Cover and refrigerate 4 hours before serving.", R.drawable.raspberrytrifle))

        adapter = FoodAdapter(this,foodlist)
        gridview.adapter = adapter
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            val email = firebaseUser.email
            binding.emailTv.text = email

        }

        else{
            startActivity(Intent(this, LoginActivity::class.java))
            Toast.makeText(this, "Successfully Logout", Toast.LENGTH_SHORT).show()
            finish()

        }

    }

    class FoodAdapter : BaseAdapter{
        var foodlist = ArrayList<Food>()
        var context : Context? = null

        constructor(context: Context?, foodlist: ArrayList<Food>) : super() {
            this.foodlist = foodlist
            this.context = context
        }


        override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
            var food = this.foodlist[index]
            var inflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var foodview = inflater.inflate(R.layout.mylayout, null)
            foodview.imageView.setImageResource(food.image!!)
            foodview.textView.text = food.name!!

            foodview.imageView.setOnClickListener{
                var intent = Intent (context,FoodDetailsActivity::class.java)
                intent.putExtra("name",food.name!!)
                intent.putExtra("des", food.des!!)
                intent.putExtra("image", food.image!!)
                context!!.startActivity(intent)
            }

            return foodview

        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return foodlist.size
        }

    }
}