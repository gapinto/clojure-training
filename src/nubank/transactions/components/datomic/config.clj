(ns nubank.transactions.components.datomic.config
  (:require [datomic.api :as api]))

(def db-uri "datomic:dev://localhost:4334/transaction")

(def schema [{:db/ident       :transaction/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/doc         "Transaction uuid"},
             {:db/ident       :transaction/account-id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/doc         "Account uuid"},
             {:db/ident       :transaction/category
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Category"},
             {:db/ident       :transaction/company
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Company"},
             {:db/ident       :transaction/date
              :db/valueType   :db.type/instant
              :db/cardinality :db.cardinality/one
              :db/doc         "Customer Name"},
             {:db/ident       :transaction/value
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "Total Value"}])

(defn open-connection []
  (api/create-database db-uri)
  (api/connect db-uri))

(defn delete-database []
  (api/delete-database db-uri))

(defn prep-datomic []
  (let [conn (open-connection)]
    (api/transact conn schema)
    conn))