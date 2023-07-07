import com.edwinacubillos.yapeproject.domain.remote.data.LocalRecipe

internal val sampleRecipe = LocalRecipe(
    ingredients = arrayListOf("Carne de res", "Sal", "Pimienta", "Chimichurri", "Vegetales asados"),
    instructions = "1. Sazona la carne de res con sal y pimienta al gusto. 2. Prepara un fuego o enciende la parrilla. 3. Coloca la carne en la parrilla y cocina a la temperatura deseada. 4. Acompaña con chimichurri y vegetales asados.",
    latitude = -34.603722,
    longitude = -58.381592,
    name ="Asado",
    originCountry = "Argentina",
    urlPicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSrDgIkZIFEuzNorjNQN3jZDQqt-x9x8UbGHQ&usqp=CAU"
)