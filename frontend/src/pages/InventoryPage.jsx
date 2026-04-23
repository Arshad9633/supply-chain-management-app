import { useEffect, useState } from "react";
import api from "../services/api";
import InventoryForm from "../components/InventoryForm";
import InventoryList from "../components/InventoryList";

export default function InventoryPage() {
  const [inventory, setInventory] = useState([]);
  const [products, setProducts] = useState([]);
  const [selectedInventory, setSelectedInventory] = useState(null);

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

  const handleEdit = (item) => {
    setSelectedInventory(item);
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/inventory/${id}`);
      fetchInventory();

      if (selectedInventory && selectedInventory.id === id) {
        setSelectedInventory(null);
      }
    } catch (err) {
      console.error("Error deleting inventory:", err);
    }
  };

  const clearSelection = () => {
    setSelectedInventory(null);
  };

  return (
    <div className="page-container">
      <h1 className="page-title">Inventory</h1>

      <div className="supplier-layout">
        <div className="card">
          <h2>{selectedInventory ? "Edit Inventory" : "Add Inventory"}</h2>
          <InventoryForm
            products={products}
            selectedInventory={selectedInventory}
            clearSelection={clearSelection}
            onInventorySaved={fetchInventory}
          />
        </div>

        <div className="card">
          <h2>Inventory List</h2>
          <InventoryList
            inventory={inventory}
            products={products}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        </div>
      </div>
    </div>
  );
}