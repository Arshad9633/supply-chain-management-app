export default function InventoryList({ inventory, products }) {
  const getProductName = (id) => {
    const p = products.find((x) => x.id === id);
    return p ? p.productName : "Unknown";
  };

  if (inventory.length === 0) {
    return <p className="empty-state">No inventory found.</p>;
  }

  return (
    <div className="supplier-list">
      {inventory.map((item) => (
        <div key={item.id} className="supplier-item">
          <div className="supplier-name">
            {getProductName(item.productId)}
          </div>

          <div className="supplier-meta">
            <div><strong>Location:</strong> {item.location}</div>
            <div><strong>Quantity:</strong> {item.quantity}</div>
          </div>
        </div>
      ))}
    </div>
  );
}