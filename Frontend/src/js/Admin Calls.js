const CategoryDropdown1 = document.getElementById("selectedCategory");
const ProductForm = document.getElementById("createProduct");
const NewsletterDiv = document.getElementById("newsletter");

const CategoryDiv = document.getElementById("categories");
const CategoryError = document.getElementById("categoryError");
const CategoryForm = document.getElementById("createCategory");

const message = document.createElement("p");

fetchData();
populateCategories();
populateCategories2();

async function populateCategories() {
    try {
        const response = await fetch("http://localhost:8080/categories/showAll");
    
        if (!response.ok) {
          throw new Error("Fail to fetch API");
        }
    
        const data = await response.json();
    
        data.forEach(element => {
          const {category_id, category_name} = element;
          
          const label = document.createElement("label");
          label.textContent = category_name + " ";

          const editbutton = document.createElement("button");
          editbutton.textContent = "Editar";
          editbutton.value = category_id;

          const deletebutton = document.createElement("button");
          deletebutton.textContent = "Borrar";
          deletebutton.value = category_id;

          const br = document.createElement("br");

          CategoryDiv.append(label);
          CategoryDiv.appendChild(editbutton);
          CategoryDiv.appendChild(deletebutton);
          CategoryDiv.appendChild(br);

          deletebutton.addEventListener('click', async (event) => {
          const id = event.target.value;
          deleteCategory(id);
        });

        });
    
       
    }
      catch(error) {
        console.error(error);
    }
}

async function populateCategories2() {
  try {
      const response = await fetch("http://localhost:8080/categories/showAll");
  
      if (!response.ok) {
        throw new Error("Fail to fetch API");
      }
  
      const data = await response.json();
  
      data.forEach(element => {
        const option = document.createElement("option");

        option.value = element.category_id;
        option.innerHTML = element.category_name;
        CategoryDropdown1.append(option);
      });
  
      
  }
    catch(error) {
      console.error(error);
  }
}

ProductForm.addEventListener("submit", function(e) {
    e.preventDefault();

    const data = new FormData(ProductForm);
    const imageInput = document.getElementById("image_upload");
    const file = imageInput.files[0];

    data.append("1", file);

    fetch("http://localhost:8080/products/add", {
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


CategoryForm.addEventListener("submit", function(e) {
  e.preventDefault();

  const formData = new FormData(CategoryForm);
  const data = formData.get("category_name");

  fetch("http://localhost:8080/categories/add", {
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


async function deleteCategory(category_id) {
  const url = "http://localhost:8080/categories/delete/" + category_id;

  try {
      CategoryError.removeChild(message);

      const response = await fetch(url, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          }
      });

      if (response.ok) {
          message.innerHTML = "Categoria Eliminada Correctamente";
          CategoryError.append(message);
      } else {
        message.innerHTML = "No se puede eliminar una categoria que esta siendo usada";
        CategoryError.append(message);
      }
  } catch (error) {
      CategoryError.append(message);
  }
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
        NewsletterDiv.append(li);
      });
    }
    catch(error) {
      console.error(error);
    }
  }