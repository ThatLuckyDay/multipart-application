package com.pet.service;

import com.pet.model.Sorter;
import com.pet.repository.SorterRepository;
import lombok.AllArgsConstructor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SorterService {

    @Autowired
    private SorterRepository sorterRepository;

    @Autowired
    @Qualifier(value = "mergeSort")
    private InplaceSort mergeSort;

    @Autowired
    private TimeAdvice timeAdvice;

    private final ProxyFactory proxyFactory = new ProxyFactory();

    public void addSortedByMergeSort(int[] array) {
        proxyFactory.addAdvice(timeAdvice);
        proxyFactory.setTarget(mergeSort);

        /* test aop */
        long start = System.nanoTime();
        try {
            ((InplaceSort)proxyFactory.getProxy()).sort(array);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        long end = System.nanoTime();

        sorterRepository
                .getSorters()
                .add(new Sorter(
                        mergeSort.getClass().getName(),
                        array.length,
                        start - end
                ));
    }

    public List<Sorter> getAllSorters() {
        return sorterRepository.getSorters();
    }
}
