import { useEffect, useState } from "react";
import api from "../services/api";
import InventoryForm from "../components/InventoryForm";
import InventoryList from "../components/InventoryList";

export default function InventoryPage() {
  const [inventory, setInventory] = useState([]);
  const [products, setProducts] = useState([]);

  const fetchInventory = async () => {
    try {
      const res = await api.get("/inventory");
      setInventory(res.data);
    } catch (err) {
      console.error("Error fetching inventory:", err);
    }
  };

  const fetchProducts = async () => {
    try {
      const res = await api.get("/products");
      setProducts(res.data);
    } catch (err) {
      console.error("Error fetching products:", err);
    }
  };

  useEffect(() => {
    fetchInventory();
    fetchProducts();
  }, []);

  return (
    <div className="page-container">
      <h1 className="page-title">Inventory</h1>

      <div className="supplier-layout">
        <div className="card">
          <h2>Add Inventory</h2>
          <InventoryForm
            products={products}
            onInventoryAdded={fetchInventory}
          />
        </div>

        <div className="card">
          <h2>Inventory List</h2>
          <InventoryList
            inventory={inventory}
            products={products}
          />
        </div>
      </div>
    </div>
  );
}