#include <set>

template<typename Key_Type, typename Compare>
  std::set<Key_Type, Compare> operator+(
      const std::set<Key_Type, Compare>& left,
      const std::set<Key_Type, Compare>& right){
  typename std::set<Key_Type, Compare> result(left);
  result.insert(right.begin(), right.end());
  return result;
}  
