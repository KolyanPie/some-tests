module Nat where
--import Prelude(Num(..), Ord(..), Eq(..), Show(..), Bool(..), error, otherwise)
import Data.Function

data Nat = Zero | Succ Nat
    deriving (Show, Eq, Ord)

instance Num Nat where
    (+) a Zero = a
    (+) a (Succ b) = Succ (a + b)

    (*) a Zero = Zero
    (*) a (Succ b) = a + (a * b)

    fromInteger 0 = Zero
    fromInteger n = Succ (fromInteger (n - 1))

    abs x = x
    signum Zero = Zero
    signum _ = Succ Zero

    negate _ = error "negate is undefined for Nat"

beside :: Nat -> Nat -> Bool
beside a b | a == Succ b = True
           | b == Succ a = True
           | otherwise = False
beside2 :: Nat -> Nat -> Bool
beside2 a b | a == Succ (Succ b) = True
            | b == Succ (Succ a) = True
            | otherwise = False
plus :: Nat -> Nat -> Nat
plus a b | a > b = a + b
         | otherwise = b + a
pow :: Nat -> Nat -> Nat
pow Zero Zero  = error "undefined"
pow Zero _ = Zero
pow x Zero = Succ Zero
pow x (Succ y) = x * (pow x y)
