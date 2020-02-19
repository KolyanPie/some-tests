module BinTree where
import Data.Function
import Nat
data BinTree a = Leaf a | Branch (BinTree a) (BinTree a)
  deriving (Show, Eq, Ord)

reverseTree :: BinTree a -> BinTree a
reverseTree (Leaf a) = Leaf a
reverseTree (Branch l r) = Branch (reverseTree r) (reverseTree l)

depth :: BinTree a -> Nat
depth (Leaf a) = Zero
depth (Branch l r) = Succ (max (depth l) (depth r))

leaves :: BinTree a -> [a]
leaves (Leaf a) = [a]
leaves (Branch l r) = (leaves l) ++ (leaves r) 

instance Num a => Num (BinTree a) where
    (+) (Leaf a) x = x
    (+) (Branch l r) x = Branch (l + x) r 
    
    (*) (Leaf a) (Leaf b) = Leaf (a * b) 
    (*) (Branch l r) (Branch l1 r1) = Branch (l * l1) (r * r1)
    (*) _ _ = error "error"

testTree :: BinTree Integer
testTree = Branch (Leaf 1) (Leaf 3) 
testTree2 = Branch (testTree) (testTree)

fix :: (a -> a) -> a
fix f = let {x = f x} in x
