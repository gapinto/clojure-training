(ns nubank.transactions.domain.logic
  (:import [java.util UUID]))


(defn new-transaction [account-id value company category date]
  {:transaction/id         (UUID/randomUUID)
   :transaction/account-id account-id
   :transaction/value      value
   :transaction/company    company
   :transaction/category   category
   :transaction/date       date})

(defn total-value-by-category [[category transactions]]
  {
   :category    category
   :total-value (->> transactions
                     (map #(% :transaction/value))
                     (reduce +))
   })

(defn categories-by-account [[account-id transactions]]
  {:account-id              account-id
   :total-value-by-category (->> (group-by :transaction/category transactions)
                                 (map total-value-by-category)
                                 (sort-by :total-value)
                                 (reverse))})

(defn total-value-by-account-and-category [transactions]
  (->> transactions
       (group-by :transaction/account-id)
       (map categories-by-account)))
