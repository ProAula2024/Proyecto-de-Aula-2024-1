const selectList = document.getElementById("multiList");
const copasList = document.getElementById("copas");
const ensaladasList = document.getElementById("ensaladas");
const specialList = document.getElementById("specials");
const newsletterRegister = document.getElementById("newslettersub");


fetchAll();

async function fetchAll() {
  fetchEnsaladas();
  fetchCopas();
  fetchFeatured()
}


async function fetchFeatured() {
  try {
    const response = await fetch("http://localhost:8080/products/showFeatured");

    if (!response.ok) {
      throw new Error("Fail to fetch API");
    }

    const data = await response.json();

    data.map(element => {
      const {product_id, product_name, product_price, product_ingredients} = element;
      selectList.innerHTML += `
        <div class="pt-40">
						<div class="transition ease-in-out hover:scale-110 bg-white rounded-xl shadow-md h-auto flex flex-col relative w-72 flex-shrink hover:-translate-y-2 duration-200">
							<img class="absolute w-full top-0 transform -translate-y-1/2" src="http://localhost:8080/api/images/${product_id}" alt="${product_name}">
							<div class="mt-32 px-6 text-black flex-1 text-center pt-10 font-opensans">
								<h2 class="font-extrabold text-3xl mt-3">${product_name}</h2>
                <h4 class="text-2xl font-bold text-gray-800 mt-3">Contiene:</h4>
                <p class="text-xl">${product_ingredients}</p>
								<h2 class="text-3xl font-bold my-5">$ ${product_price}</h2>
							</div>
						</div>
					</div>
      `
    });

    
  } catch(error) {
    console.error(error);
  }
}


async function fetchCopas() {
  try {
    const response = await fetch("http://localhost:8080/products/showCopas");

    if (!response.ok) {
      throw new Error("Fail to fetch API");
    }

    const data = await response.json();

    data.map(element => {
      const {product_id, product_name, product_price, product_ingredients} = element;
      copasList.innerHTML += `
        <div class="pt-20">
						<div class="transition ease-in-out hover:scale-110 bg-white rounded-xl shadow-md h-auto flex flex-col relative w-72 flex-shrink hover:-translate-y-2 duration-200">
							<img class="absolute w-full top-0 transform -translate-y-1/2" src="http://localhost:8080/api/images/${product_id}">
							<div class="mt-32 px-6 text-black flex-1 text-center pt-10 font-opensans">
								<h2 class="font-extrabold text-3xl mt-3">${product_name}</h2>
                <h4 class="text-2xl font-bold text-gray-800 mt-3">Contiene:</h4>
                <p class="text-xl">${product_ingredients}</p>
								<h2 class="text-3xl font-bold my-5">$ ${product_price}</h2>
							</div>
						</div>
					</div>
      `
    }); 
  }
  catch(error) {
    console.error(error);
  }
}


async function fetchEnsaladas() {
  try {
    const response = await fetch("http://localhost:8080/products/showEnsaladas");

    if (!response.ok) {
      throw new Error("Fail to fetch API");
    }

    const data = await response.json();

    data.map(element => {
      const {product_id, product_name, product_price, product_ingredients} = element;
      ensaladasList.innerHTML += `
        <div class="pt-20">
						<div class="transition ease-in-out hover:scale-110 bg-white rounded-xl shadow-md h-auto flex flex-col relative w-72 flex-shrink hover:-translate-y-2 duration-200">
							<img class="absolute w-full top-0 transform -translate-y-1/2" src="http://localhost:8080/api/images/${product_id}">
							<div class="mt-32 px-6 text-black flex-1 text-center pt-10 font-opensans">
								<h2 class="font-extrabold text-3xl mt-3">${product_name}</h2>
                <h4 class="text-2xl font-bold text-gray-800 mt-3">Contiene:</h4>
                <p class="text-xl">${product_ingredients}</p>
								<h2 class="text-3xl font-bold my-5">$ ${product_price}</h2>
							</div>
						</div>
					</div>
      `
    }); 
  }
  catch(error) {
    console.error(error);
  }
}


newsletterRegister.addEventListener("submit", function(e) {
  e.preventDefault();

  const formData = new FormData(newsletterRegister);
  const data = formData.get("email");

  fetch("http://localhost:8080/newsletter/add", {
      method: "POST",
      body: data
  })

  .then( res => res.json())
  .then( data => {
      console.log(data);
  })

  .catch(error => {
      console.error('Error:', error);
  })

});