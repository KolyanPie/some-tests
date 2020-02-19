module FixRewrite where
import Prelude hiding   ( repeat
                        , map
                        , foldr
                        , foldl
                        , iterate
                        , zip
                        , zipWith
                        , cycle
                        )
import Data.Function (fix)
import Nat
data BinTree a = Leaf a | Node ( BinTree a) ( BinTree a)
     deriving (Show, Eq, Ord)
-- map через fix
map :: (a -> b) -> [a] -> [b]
map f = fix $ \m xs -> case xs of
    []   -> []
    y:ys -> f y : m ys

-- правая свертка через fix
foldr :: (a -> b -> b) -> b -> [a] -> b
foldr f = fix $ \fr ini xs -> case xs of
    []      -> ini
    y:ys    -> y `f` fr ini ys

-- левая свертка через fix
foldl :: (b -> a -> b) -> b -> [a] -> b
foldl f = fix $ \fl ini xs -> case xs of
    []      -> ini
    y:ys    -> f ini y `fl` ys

-- zip'ы через fix
zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith f = fix $ \z x y -> if null x || null y 
    then [] 
    else f (head x) (head y) : z (tail x) (tail y)

zip :: [a] -> [b] -> [(a, b)]
zip = zipWith (,)


-- repeat через fix
repeat :: a -> [a]
repeat = fix . (:) 

--cycle' :: BinTree a -> BinTree a
--cycle' a = fix .
--cycle' = fix . (:)

--iterate через fix
iterate :: (a -> a) -> a -> [a]
iterate f = fix $ \i x -> x : i (f x)
cycle :: [a] -> [a]
cycle = \xs -> fix (xs ++)



    
treeAdd :: Num a => BinTree a -> BinTree a -> BinTree a
treeAdd = fix $ \m tl tr -> case (tl, tr) of
        ((Leaf _),x) -> x
        ((Node l r),x) -> Node (m l x) r

testTree :: BinTree Int
testTree = Node
             (Node
                  (Node
                       (Leaf 6)
                       (Node
                           (Node (Leaf 5) (Leaf 2))
                           (Leaf 9)))
                  (Node (Leaf 7) (Leaf 8)))
              (Node
                  (Node
                        (Leaf 8)
                        (Node (Leaf 4) (Leaf 9)))
                  (Node (Leaf 3) (Leaf 1)))
main :: IO ()
main = print $ treeAdd testTree testTree



-- sdfsdfs
