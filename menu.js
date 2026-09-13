// NextTurn College Canteen Menu
// You can easily change, add or remove items from here later.

const menu = [
  // Snacks
  {
    id: 1,
    name: "Chips",
    category: "Snacks",
    price: 10,
    available: true
  },
  {
    id: 2,
    name: "Kurkure",
    category: "Snacks",
    price: 10,
    available: true
  },
  {
    id: 3,
    name: "Samosa",
    category: "Snacks",
    price: 15,
    available: true
  },
  {
    id: 4,
    name: "Vada Pav",
    category: "Snacks",
    price: 20,
    available: true
  },

  // Fast Food
  {
    id: 5,
    name: "Veg Sandwich",
    category: "Fast Food",
    price: 40,
    available: true
  },
  {
    id: 6,
    name: "Cheese Sandwich",
    category: "Fast Food",
    price: 50,
    available: true
  },
  {
    id: 7,
    name: "Veg Burger",
    category: "Fast Food",
    price: 60,
    available: true
  },
  {
    id: 8,
    name: "French Fries",
    category: "Fast Food",
    price: 50,
    available: true
  },
  {
    id: 9,
    name: "Veg Roll",
    category: "Fast Food",
    price: 45,
    available: true
  },

  // Meals
  {
    id: 10,
    name: "Veg Biryani",
    category: "Meals",
    price: 80,
    available: true
  },
  {
    id: 11,
    name: "Veg Thali",
    category: "Meals",
    price: 90,
    available: true
  },
  {
    id: 12,
    name: "Misal Pav",
    category: "Meals",
    price: 45,
    available: true
  },

  // Drinks
  {
    id: 13,
    name: "Frooti",
    category: "Drinks",
    price: 20,
    available: true
  },
  {
    id: 14,
    name: "Masala Chai",
    category: "Drinks",
    price: 15,
    available: true
  },
  {
    id: 15,
    name: "Cold Coffee",
    category: "Drinks",
    price: 40,
    available: true
  },
  {
    id: 16,
    name: "Lemon Juice",
    category: "Drinks",
    price: 25,
    available: true
  },
  {
    id: 17,
    name: "Soft Drink",
    category: "Drinks",
    price: 30,
    available: true
  }
];


// Service charge rule
// ₹0.50 for items priced ₹20 or below
// ₹1.00 for items priced above ₹20

function getServiceCharge(price) {
  return price <= 20 ? 0.50 : 1.00;
}


// Calculate total for one food item
function getItemTotal(price, quantity) {
  const serviceCharge = getServiceCharge(price);
  return (price * quantity) + (serviceCharge * quantity);
}


// Make menu available to other JavaScript files
if (typeof module !== "undefined") {
  module.exports = {
    menu,
    getServiceCharge,
    getItemTotal
  };
  }
