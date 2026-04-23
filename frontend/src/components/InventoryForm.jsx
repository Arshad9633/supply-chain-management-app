import { useState } from "react";
import api from "../services/api";

export default function InventoryForm({ products, onInventoryAdded }) {
  const [formData, setFormData] = useState({
    productId: "",
    location: "",
    quantity: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await api.post("/inventory", {
        productId: formData.productId,
        location: formData.location,
        quantity: Number(formData.quantity),
      });

      setFormData({
        productId: "",
        location: "",
        quantity: "",
      });

      onInventoryAdded();
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="form-group">
        <label>Product</label>
        <select name="productId" value={formData.productId} onChange={handleChange}>
          <option value="">Select Product</option>
          {products.map((p) => (
            <option key={p.id} value={p.id}>
              {p.productName}
            </option>
          ))}
        </select>
      </div>

      <div className="form-group">
        <label>Location</label>
        <input
          name="location"
          value={formData.location}
          onChange={handleChange}
        />
      </div>

      <div className="form-group">
        <label>Quantity</label>
        <input
          type="number"
          name="quantity"
          value={formData.quantity}
          onChange={handleChange}
        />
      </div>

      <button type="submit">Add Inventory</button>
    </form>
  );
}