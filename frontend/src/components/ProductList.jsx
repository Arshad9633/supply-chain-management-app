export default function ProductList({ products, suppliers, onEdit, onDelete }) {
  const getSupplierName = (supplierId) => {
    const s = suppliers.find((x) => x.id === supplierId);
    return s ? s.supplierName : "Unknown";
  };

  if (products.length === 0) {
    return <p className="empty-state">No products found.</p>;
  }

  return (
    <div className="supplier-list">
      {products.map((p) => (
        <div key={p.id} className="supplier-item">
          <div className="supplier-name">{p.productName}</div>

          <div className="supplier-meta">
            <div><strong>Category:</strong> {p.category}</div>
            <div><strong>Cost:</strong> {p.unitCost}</div>
            <div><strong>Supplier:</strong> {getSupplierName(p.supplierId)}</div>
          </div>

          <div className="action-buttons">
            <button onClick={() => onEdit(p)}>Edit</button>
            <button className="delete-btn" onClick={() => onDelete(p.id)}>
              Delete
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}