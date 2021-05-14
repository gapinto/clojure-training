(ns nubank.transactions.domain.logic
  (:import [java.util UUID]))


(defn new-transaction [account-id value company category date]
  {:id         (UUID/randomUUID)
   :account-id account-id
   :value      value
   :company    company
   :category   category
   :date       date})

(defn total-value-by-category [[category transactions]]
  {
   :category    category
   :total-value (->> transactions
                     (map #(% :value))
                     (reduce +))
   })

(defn categories-by-account [[account-id transactions]]
  {:account-id              account-id
   :total-value-by-category (->> (group-by :category transactions)
                                 (map total-value-by-category)
                                 (sort-by :total-value)
                                 (reverse))})

(defn total-value-by-account-and-category [transactions]
  (->> transactions
       (vals)
       (group-by :account-id)
       (map categories-by-account)))
