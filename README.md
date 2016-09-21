TDD training app in e-commerce domain
=====================================

Backlog:
--------

  * Update quantity of a product already in cart (ie. add one `Cup`, then add another). 
  * Add two different products to cart (ie. add one `Cup` and then add a `Plate`).
  * Calculate total if more then one piece of a product in the Cart (ie. 2 `Cup`s for `2,50` each yields a total of `5,00`).
  * Calculate total for cart with many products in different quantities.
  * Cart contains a fixed shipping cost. Total includes shipping now (ie. `GET /user/6/cart` returns `$.shipping = 15`).
  * Offer free shipping if cart value bigger than $100.
  * Place an order (ie. `POST /user/6/cart/purchase` creates an `Order` with `OrderItems`)
  