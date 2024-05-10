const multiList = document.getElementById("multiList");
const copasList = document.getElementById("copas");
const conosList = document.getElementById("conos");
const specialList = document.getElementById("specials");

fetchData();
fetchAll();

async function fetchAll() {
  fetchConos();
  fetchCopas();
  fetchFeatured()
}


async function fetchData() {

  try {
    const response = await fetch("http://localhost:8080/newsletter/showAll");

    if (!response.ok) {
      throw new Error("Fail to fetch API");
    }

    const data = await response.json();

    data.forEach(element => {
      const li = document.createElement("li");
      li.textContent = element.registered_email;
      list.append(li);
    });
  }
  catch(error) {
    console.error(error);
  }
}


async function fetchFeatured() {
  try {
    const response = await fetch("http://localhost:8080/products/showFeatured");

    if (!response.ok) {
      throw new Error("Fail to fetch API");
    }

    const data = await response.json();

    data.map(element => {
      const {product_name, product_price, product_image_url, product_ingredients} = element;
      multiList.innerHTML += `
        <div class="pt-20">
						<div class="transition ease-in-out hover:scale-110 bg-white rounded-xl shadow-md h-80 flex flex-col relative w-72 flex-shrink hover:-translate-y-2 duration-200">
							<img class="absolute w-full top-0 transform -translate-y-1/2" src="${product_image_url}">
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


async function fetchCopas() {
  try {
    const response = await fetch("http://localhost:8080/products/showCopas");

    if (!response.ok) {
      throw new Error("Fail to fetch API");
    }

    const data = await response.json();

    data.map(element => {
      const {product_name, product_price, product_image_url, product_ingredients} = element;
      multiList.innerHTML += `
        <div class="pt-20">
						<div class="transition ease-in-out hover:scale-110 bg-white rounded-xl shadow-md h-80 flex flex-col relative w-72 flex-shrink hover:-translate-y-2 duration-200">
							<img class="absolute w-full top-0 transform -translate-y-1/2" src="${product_image_url}">
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


async function fetchConos() {
  try {
    const response = await fetch("http://localhost:8080/products/showConos");

    if (!response.ok) {
      throw new Error("Fail to fetch API");
    }

    const data = await response.json();

    data.map(element => {
      const {product_name, product_price, product_image_url, product_ingredients} = element;
      multiList.innerHTML += `
        <div class="pt-20">
						<div class="transition ease-in-out hover:scale-110 bg-white rounded-xl shadow-md h-80 flex flex-col relative w-72 flex-shrink hover:-translate-y-2 duration-200">
							<img class="absolute w-full top-0 transform -translate-y-1/2" src="${product_image_url}">
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