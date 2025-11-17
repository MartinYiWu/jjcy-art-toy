package com.designer.toys.product.service.impl;

import com.designer.toys.product.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements IProductService {
}
