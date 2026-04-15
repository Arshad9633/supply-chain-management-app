import { useEffect, useState } from "react";
import api from "../services/api";
import SupplierForm from "../components/SupplierForm";
import SupplierList from "../components/SupplierList";

export default function SuppliersPage() {
  const [suppliers, setSuppliers] = useState([]);
  const [selectedSupplier, setSelectedSupplier] = useState(null);

  const fetchSuppliers = async () => {
    try {
      const response = await api.get("/suppliers");
      setSuppliers(response.data);
    } catch (error) {
      console.error("Error fetching suppliers:", error);
    }
  };

  useEffect(() => {
    fetchSuppliers();
  }, []);

  const handleEdit = (supplier) => {
    setSelectedSupplier(supplier);
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/suppliers/${id}`);
      fetchSuppliers();

      if (selectedSupplier && selectedSupplier.id === id) {
        setSelectedSupplier(null);
      }
    } catch (error) {
      console.error("Error deleting supplier:", error);
    }
  };

  const clearSelection = () => {
    setSelectedSupplier(null);
  };

  return (
    <div className="page-container">
      <h1 className="page-title">Suppliers</h1>

      <div className="supplier-layout">
        <div className="card">
          <h2>{selectedSupplier ? "Edit Supplier" : "Add Supplier"}</h2>
          <SupplierForm 
            onSupplierSaved={fetchSuppliers} 
            selectedSupplier={selectedSupplier}
            clearSelection={clearSelection}
          />
        </div>

        <div className="card">
          <h2>Supplier List</h2>
          <SupplierList 
            suppliers={suppliers} 
            onEdit={handleEdit} 
            onDelete={handleDelete} 
          />
        </div>
      </div>
    </div>
  );
}