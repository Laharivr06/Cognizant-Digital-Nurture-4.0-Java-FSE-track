import React from "react";
import './Cart.css';

class Cart extends React.Component {
  render() {
    return (
      <table className="cart-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Price</th>
          </tr>
        </thead>

        <tbody>

          {this.props.item.map((item) => {
            return (
              <tr>
                <td>{item.itemname}</td>
                <td>{item.price}</td>
              </tr>
            )})
          }

        </tbody>
      </table>
    );
  }
}

export default Cart;
