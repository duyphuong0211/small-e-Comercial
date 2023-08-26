package com.example.demo.service;
import com.example.demo.dto.RequestDto;
import com.example.demo.dto.SearchRequestDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilterSpecification<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {

        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()), searchRequestDto.getValue());
            }
        };
    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos, RequestDto.Operator operator) {
        return(root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            for(SearchRequestDto requestDto : searchRequestDtos) {

                switch (requestDto.getOperation()) {

                    case EQUAL:
                        Predicate equal =  criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(equal);
                        break;

                    case LIKE:
                        Predicate like =  criteriaBuilder.like(root.get(requestDto.getColumn()), "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                        break;

                     case IN:
                         String[] split = requestDto.getValue().split(",");
                         Predicate in = root.get(requestDto.getColumn()).in(Arrays.asList(split));
                         predicates.add(in);
                         break;

                    case LESS_THAN:
                        Predicate lessThan =  criteriaBuilder.lessThan(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(lessThan);
                        break;

                    case GREATER_THAN:
                        Predicate greaterThan =  criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(greaterThan);
                        break;

                    case BETWEEN:
                        //"10, 20"
                        String[] splitBtw = requestDto.getValue().split(",");
                        Predicate btw = criteriaBuilder.between(root.get(requestDto.getColumn()), Long.parseLong(splitBtw[0]), Long.parseLong(splitBtw[1]));
                        predicates.add(btw);
                        break;

                    case JOIN:
                        Predicate join = criteriaBuilder.equal(root.join(requestDto.getJoinTable()).get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(join);
                        break;
                }
            }

            if(operator.equals(RequestDto.Operator.AND)){
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
